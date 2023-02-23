package com.bluehabit.budgetku.sdk.user

import com.bluehabit.budgetku.model.BaseResponse
import com.bluehabit.budgetku.model.SignInEmailRequest
import com.bluehabit.budgetku.model.UserResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class UserApi(
    private val client: HttpClient,
    private val baseUrl: String
) {

    suspend fun signInWithEmail(email: String, password: String) =
        client.post("${baseUrl}/v1/sign-in-email") {
            contentType(ContentType.Application.Json)
            setBody(SignInEmailRequest(email, password))
        }.body<BaseResponse<UserResponse>>()


}