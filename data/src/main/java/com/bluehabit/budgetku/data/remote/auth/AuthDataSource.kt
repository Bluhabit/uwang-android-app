/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.remote.auth

import android.content.SharedPreferences
import com.bluehabit.budgetku.data.common.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.plugins.resources.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val httpClient: HttpClient,
    private val sharedPreferences: SharedPreferences
) {
    suspend fun signInWithEmail(
        email: String,
        password: String
    ) = safeApiCall<String>(
        onSaveToken = {
            val sp = sharedPreferences.edit()
            sp.putBoolean("isLoggedIn", true)
            sp.putString("token", it)
            sp.apply()
        }
    ) {
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
    ) = safeApiCall<String>(
        onSaveToken = {
            val sp = sharedPreferences.edit()
            sp.putBoolean("isLoggedIn", true)
            sp.putString("token", it)
            sp.apply()
        }
    ) {
        httpClient.post(AuthApi.SignInGoogle()) {
            setBody(mapOf("token" to token))
        }
    }
}