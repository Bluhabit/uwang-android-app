/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.data.authentication.repositories

import android.content.SharedPreferences
import com.bluhabit.blu.android.data.authentication.datasource.remote.AuthApi
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.SignIntGoogleRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.response.SignInGoogleResponse
import com.bluhabit.blu.data.common.Response
import com.bluhabit.blu.data.common.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.plugins.resources.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val sharedPreferences: SharedPreferences
) {
    suspend fun signInGoogle(
        token: String
    ): Response<SignInGoogleResponse> {
        return when (val result = safeApiCall<SignInGoogleResponse> {
            httpClient.post(AuthApi.SignInGoogle()) {
                setBody(SignIntGoogleRequest(token))
            }
        }) {
            is Response.Error -> result
            is Response.Result -> {
                //set session to share pref
                sharedPreferences.edit().apply {
                    putBoolean("isLoggedIn", true)
                    putString("token", result.data.token)
                    apply()
                }
                Response.Result(result.data)
            }
        }
    }
}