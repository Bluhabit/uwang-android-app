/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.repositories

import com.bluehabit.eureka.data.authentication.datasource.remote.AuthApi
import com.bluehabit.eureka.data.authentication.datasource.remote.request.SignInWithEmailRequest
import com.bluehabit.eureka.data.authentication.datasource.remote.request.SignInWithGoogleRequest
import com.bluehabit.eureka.data.authentication.datasource.remote.response.SignInResponse
import com.bluehabit.eureka.data.common.Response
import com.bluehabit.eureka.data.common.safeApiCall
import com.bluehabit.eureka.data.persistence.SharedPref
import io.ktor.client.HttpClient
import io.ktor.client.plugins.resources.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class SignInRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val pref: SharedPref
) {
    suspend fun signInWIthEmail(email: String, password: String): Response<SignInResponse> =
        when (val result = safeApiCall<SignInResponse> {
            httpClient.post(AuthApi.SignInWithEmail()) {
                setBody(SignInWithEmailRequest(email, password))
            }
        }) {
            is Response.Result -> {
                pref.setUserLoggedIn(result.data.token)
                result
            }

            else -> result
        }

    suspend fun signInWithGoogle(token: String): Response<Any> =
        when (val result = safeApiCall<SignInResponse> {
            httpClient.post(AuthApi.SignInWithEmail()) {
                setBody(SignInWithGoogleRequest(token))
            }
        }) {
            is Response.Result -> {
                pref.setUserLoggedIn(result.data.token)
                result
            }

            else -> result
        }
}