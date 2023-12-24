/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.data.authentication.repositories

import android.content.SharedPreferences
import androidx.core.content.edit
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.SignInBasicRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.SignInGoogleRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.SignUpBasicRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.VerifyOtpSignInRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.VerifyOtpSignUpRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.response.SignInBasicResponse
import com.bluhabit.blu.data.common.Response
import com.bluhabit.blu.data.common.safeApiCall
import com.bluhabit.blu.data.datasource.remote.response.SignInGoogleResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val sharedPreferences: SharedPreferences
) {
    companion object {
        const val KEY_SESSION_ID = "session_id"
    }

    //sign in region
    suspend fun signInBasic(email: String): Response<String> {
        return when (val result = safeApiCall<String> {
            httpClient.post("/api/auth/sign-in-basic") {
                setBody(SignInBasicRequest(email = email))
            }
        }) {
            is Response.Error -> result
            is Response.Result -> {
                if (result.data.isNotEmpty()) {
                    sharedPreferences.edit {
                        putString(KEY_SESSION_ID, result.data)
                        apply()
                    }
                }
                result
            }
        }
    }

    suspend fun verifyOtpSignInBasic(
        otp: String
    ): Response<SignInBasicResponse> {
        val sessionId = sharedPreferences.getString(KEY_SESSION_ID, null)
            ?: Response.Error("Sesi tidak ditemukan sudah habis", 401)
        return when (val result = safeApiCall<SignInBasicResponse> {
            httpClient.post("/api/auth/sign-in-basic/verify-otp") {
                setBody(
                    VerifyOtpSignInRequest(
                        sessionId = sessionId.toString(),
                        otp = otp
                    )
                )
            }
        }) {
            is Response.Error -> result
            is Response.Result -> {
                sharedPreferences.edit {
                    remove(KEY_SESSION_ID)
                    apply()
                }
                result
            }
        }
    }

    suspend fun signInGoogle(
        token: String
    ): Response<SignInGoogleResponse> {
        return when (val result = safeApiCall<SignInGoogleResponse> {
            httpClient.post("/api/auth/sign-in-google") {
                setBody(SignInGoogleRequest(token))
            }
        }) {
            is Response.Error -> result
            is Response.Result -> {
                //set session to share pref
                sharedPreferences.edit {
                    putBoolean("isLoggedIn", true)
                    putString("token", result.data.token)
                    putString("", "")
                    apply()
                }
                Response.Result(result.data)
            }
        }
    }

    //end sign in
    //sign up region

    suspend fun signUpBasic(
        fullName: String,
        email: String,
        password: String
    ): Response<String> {
        return when (val result = safeApiCall<String> {
            httpClient.post("/auth/sign-up-basic") {
                setBody(
                    SignUpBasicRequest(
                        fullName = fullName,
                        email = email,
                        password = password
                    )
                )
            }
        }) {
            is Response.Error -> result
            is Response.Result -> {
                if (result.data.isNotEmpty()) {
                    sharedPreferences.edit {
                        putString(KEY_SESSION_ID, result.data)
                        apply()
                    }
                }
                result
            }
        }
    }

    suspend fun verifyOtpSignUpBasic(otp: String): Response<String> {
        val sessionId = sharedPreferences.getString(KEY_SESSION_ID, null)
            ?: Response.Error("Sesi tidak ditemukan sudah habis", 401)

        return when (val result = safeApiCall<String> {
            httpClient.post("/auth/sign-up-basic/verify-otp") {
                setBody(
                    VerifyOtpSignUpRequest(
                        sessionId = sessionId.toString(),
                        otp = otp
                    )
                )
            }
        }) {
            is Response.Error -> result
            is Response.Result -> {
                sharedPreferences.edit {
                    remove(KEY_SESSION_ID)
                    apply()
                }
                result
            }
        }
    }
    //end sign up
    //

}