/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.repositories

import com.bluehabit.eureka.data.authentication.datasource.remote.AuthApi
import com.bluehabit.eureka.data.authentication.datasource.remote.request.SignInWithEmailRequest
import com.bluehabit.eureka.data.authentication.datasource.remote.response.SignInWithEmailResponse
import com.bluehabit.eureka.data.common.Response
import com.bluehabit.eureka.data.common.safeApiCall
import com.bluehabit.eureka.data.persistence.SharedPref
import io.ktor.client.HttpClient
import io.ktor.client.plugins.resources.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class SignInWithEmailRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val pref: SharedPref
) {
    suspend fun execute(email: String, password: String): Response<SignInWithEmailResponse> {
        val result = safeApiCall<SignInWithEmailResponse> {
            httpClient.post(AuthApi.SignInWithEmail()) {
                setBody(
                    SignInWithEmailRequest(
                        email,
                        password
                    )
                )
            }
        }
        when(result){
            is Response.Result -> {
                pref.setUserLoggedIn("this is token")
            }
            else->Unit
        }
        return result
    }
}