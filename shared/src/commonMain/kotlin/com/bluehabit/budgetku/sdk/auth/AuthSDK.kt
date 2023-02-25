package com.bluehabit.budgetku.sdk.auth

import com.bluehabit.budgetku.DriverFactory
import com.bluehabit.budgetku.createDatabase
import com.bluehabit.budgetku.entity.UserModel
import com.bluehabit.budgetku.model.BaseResponse
import com.bluehabit.budgetku.model.SignInEmailRequest
import com.bluehabit.budgetku.model.SignInGoogleRequest
import com.bluehabit.budgetku.model.UserResponse
import com.bluehabit.budgetku.sharedPref.KMMPreference
import com.bluehabit.budgetku.utils.Response
import com.bluehabit.budgetku.utils.safeApiCall
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.resources.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.logging.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthSDK(
    private val driverFactory: DriverFactory,
    private val client: HttpClient,
    private val pref: KMMPreference
) {
    private val db = createDatabase(driverFactory)
    suspend fun isLoggedIn(): Boolean = pref.getString("USER") != null

    @Throws(
        Exception::class
    )
    suspend fun signInWithEmail(
        email: String,
        password: String,
    ): Flow<Response<BaseResponse<UserResponse>>> = flow {
        emit(Response.Loading)
        val res = safeApiCall<UserResponse>(
            onSaveToken = {
                pref.put("USER", it)
            }
        ) {
            client.post(AuthApi.SignInWithEmail()) {
                setBody(
                    SignInEmailRequest(
                        email,
                        password
                    )
                )
            }
        }
        emit(res)
    }.flowOn(Dispatchers.Default)

    @Throws(
        Exception::class
    )
    suspend fun signInGoogle(
        token: String
    ): Flow<Response<BaseResponse<UserResponse>>> = flow {
        emit(Response.Loading)
        val res = safeApiCall<UserResponse> {
            client.post(AuthApi.SignInGoogle()) {
                setBody(
                    SignInGoogleRequest(
                        token = token
                    )
                )
            }
        }
        emit(res)
    }
}