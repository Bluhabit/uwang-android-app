/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.remote.auth

import com.bluehabit.budgetku.data.common.safeApiCall
import com.bluehabit.budgetku.data.local.SharedPref
import io.ktor.client.HttpClient
import io.ktor.client.plugins.resources.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val httpClient: HttpClient,
    private val pref: SharedPref
) {
    suspend fun signInWithEmail(
        email: String,
        password: String
    ) = safeApiCall<String>(onSaveToken = { pref.setUserLoggedIn(token = it) }) {
        httpClient.post(AuthApi.SignInWithEmail()) {
            setBody(
                mapOf(
                    "email" to email,
                    "password" to password
                )
            )
        }
    }

    suspend fun signInWithGoogle(
        token: String
    ) = safeApiCall<String>(onSaveToken = { pref.setUserLoggedIn(token = it) }) {
        httpClient.post(AuthApi.SignInGoogle()) {
            setBody(mapOf("token" to token))
        }
    }
}