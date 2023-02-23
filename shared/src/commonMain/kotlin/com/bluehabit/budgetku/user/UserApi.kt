package com.bluehabit.budgetku.user

import com.bluehabit.budgetku.model.BaseResponse
import com.bluehabit.budgetku.model.UserRequest
import com.bluehabit.budgetku.model.UserResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class UserApi(
    private val client: HttpClient
) {
    private val BASE_URL = "http://192.168.1.32:3003"

    suspend fun signInWithEmail(email: String, password: String) =
        client.post("${BASE_URL}/v1/sign-in-email") {
            contentType(ContentType.Application.Json)
            setBody(UserRequest(email, password))
        }.body<BaseResponse<UserResponse>>()
}