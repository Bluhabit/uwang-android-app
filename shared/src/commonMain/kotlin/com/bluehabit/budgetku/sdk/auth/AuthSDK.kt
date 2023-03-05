/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.sdk.auth

import app.cash.sqldelight.logs.LogSqliteDriver
import com.bluehabit.budgetku.DriverFactory
import com.bluehabit.budgetku.createDatabase
import com.bluehabit.budgetku.model.BaseResponse
import com.bluehabit.budgetku.model.SignInEmailRequest
import com.bluehabit.budgetku.model.SignInGoogleRequest
import com.bluehabit.budgetku.model.SignUpEmailRequest
import com.bluehabit.budgetku.model.SignUpGoogleRequest
import com.bluehabit.budgetku.model.UserResponse
import com.bluehabit.budgetku.model.toEntity
import com.bluehabit.budgetku.sharedPref.KMMPreference
import com.bluehabit.budgetku.utils.Response
import com.bluehabit.budgetku.utils.getValue
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

const val KEY_TOKEN = "SAJKSNF"
class AuthSDK(
    driverFactory: DriverFactory,
    private val client: HttpClient,
    private val pref: KMMPreference
) {
    private val db = createDatabase(driverFactory)
    fun isLoggedIn(): Boolean = pref.getString(KEY_TOKEN) != null


    @Throws(
        Exception::class
    )
    suspend fun signInWithEmail(
        email: String,
        password: String,
    ): Flow<Response<BaseResponse<UserResponse>>> = flow {
        emit(Response.Loading)
        val res = safeApiCall<UserResponse>(onSaveToken = { pref.put(KEY_TOKEN, it) }) {
            client.post(AuthApi.SignInWithEmail()) { setBody(SignInEmailRequest(email, password)) }
        }
        res.getValue {
            with(it.data) {
                db.userQueries.insertUser(
                    userId = userId.orEmpty(),
                    userFullName = userProfile?.userFullName.orEmpty(),
                    userCountryCode = userProfile?.userCountryCode.orEmpty(),
                    userEmail = userEmail,
                    userDateOfBirth = userProfile?.userDateOfBirth.orEmpty(),
                    userPhoneNumber = userPhoneNumber,
                    userProfilePicture = userProfile?.userProfilePicture.orEmpty(),
                    userStatus = userStatus,
                    createdAt = createdAt,
                    updatedAt = updatedAt
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
        val res = safeApiCall<UserResponse>(onSaveToken = { pref.put(KEY_TOKEN, it) }) {
            client.post(AuthApi.SignInGoogle()) {
                setBody(SignInGoogleRequest(token))
            }
        }
        res.getValue {
            with(it.data) {
                db.userQueries.insertUser(
                    userId = userId.orEmpty(),
                    userFullName = userProfile?.userFullName.orEmpty(),
                    userCountryCode = userProfile?.userCountryCode.orEmpty(),
                    userEmail = userEmail,
                    userDateOfBirth = userProfile?.userDateOfBirth.orEmpty(),
                    userPhoneNumber = userPhoneNumber,
                    userProfilePicture = userProfile?.userProfilePicture.orEmpty(),
                    userStatus = userStatus,
                    createdAt = createdAt,
                    updatedAt = updatedAt
                )
            }
        }
        emit(res)
    }.flowOn(Dispatchers.Default)

    @Throws(
        Exception::class
    )
    suspend fun signUpWithEmail(
        fullName: String,
        email: String,
        password: String
    ): Flow<Response<BaseResponse<UserResponse>>> = flow {
        emit(Response.Loading)
        val res = safeApiCall<UserResponse> {
            client.post(AuthApi.SignUpWithEmail()) {
                setBody(SignUpEmailRequest(fullName, email, password))
            }
        }
        emit(res)
    }.flowOn(Dispatchers.Default)

    @Throws(
        Exception::class
    )
    suspend fun signUpGoogle(
        token: String
    ): Flow<Response<BaseResponse<UserResponse>>> = flow {
        emit(Response.Loading)
        val res = safeApiCall<UserResponse> {
            client.post(AuthApi.SignUpWithGoogle()) {
                setBody(SignUpGoogleRequest(token))
            }
        }
        emit(res)
    }.flowOn(Dispatchers.Default)
}