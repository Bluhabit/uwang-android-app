/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.data.authentication.repositories

import android.content.SharedPreferences
import androidx.core.content.edit
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.ForgotPasswordRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.SetForgotPasswordRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.SignInBasicRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.SignInGoogleRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.SignUpBasicRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.VerifyOtpForgotPasswordRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.VerifyOtpSignInRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.VerifyOtpSignUpRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.response.ForgotPasswordResponse
import com.bluhabit.blu.android.data.authentication.datasource.remote.response.SignInBasicResponse
import com.bluhabit.blu.android.data.authentication.datasource.remote.response.SignInGoogleResponse
import com.bluhabit.blu.android.data.authentication.datasource.remote.response.SignUpBasicResponse
import com.bluhabit.blu.data.common.Response
import com.bluhabit.blu.data.common.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val sharedPreferences: SharedPreferences
) {
    companion object {
        const val KEY_SESSION_ID = "d0ca-fc40"
        const val KEY_TOKEN = "abc0-df12"
        const val KEY_IS_LOGGED_IN = "f435-bc0f2"
    }

    //session
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }
    //end session

    //sign in region
    suspend fun signInBasic(email: String, password: String): Response<String> {
        return when (val result = safeApiCall<String> {
            httpClient.post("/api/auth/sign-in-basic") {
                setBody(SignInBasicRequest(email = email, password = password))
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
            ?: return Response.Error("Sesi tidak ditemukan sudah habis", 401)
        return when (val result = safeApiCall<SignInBasicResponse> {
            httpClient.post("/api/auth/sign-in-basic/verify-otp") {
                setBody(
                    VerifyOtpSignInRequest(
                        sessionId = sessionId,
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
                    putBoolean(KEY_IS_LOGGED_IN, true)
                    putString(KEY_TOKEN, result.data.token)
                    apply()
                }
                Response.Result(result.data)
            }
        }
    }

    //end sign in
    //sign up region

    suspend fun signUpBasic(
        email: String,
        password: String
    ): Response<String> {
        return when (val result = safeApiCall<String> {
            httpClient.post("/api/auth/sign-up-basic") {
                setBody(
                    SignUpBasicRequest(
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

    suspend fun verifyOtpSignUpBasic(otp: String): Response<SignUpBasicResponse> {
        val sessionId = sharedPreferences.getString(KEY_SESSION_ID, null)
            ?: return Response.Error("Sesi tidak ditemukan sudah habis", 401)

        return when (val result = safeApiCall<SignUpBasicResponse> {
            httpClient.post("/api/auth/sign-up-basic/verify-otp") {
                setBody(
                    VerifyOtpSignUpRequest(
                        sessionId = sessionId,
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
    //forgot password
    suspend fun forgotPassword(email: String): Response<String> {
        return when (val result = safeApiCall<String> {
            httpClient.post("/api/auth/forgot-password") {
                setBody(ForgotPasswordRequest(email = email))
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

    suspend fun verifyOtpForgotPassword(
        otp: String
    ): Response<ForgotPasswordResponse> {
        val sessionId = sharedPreferences.getString(KEY_SESSION_ID, null)
            ?: return Response.Error("Sesi tidak ditemukan sudah habis", 401)

        return when (val result = safeApiCall<ForgotPasswordResponse> {
            httpClient.post("/api/forgot-password/verify-otp") {
                setBody(
                    VerifyOtpForgotPasswordRequest(
                        sessionId = sessionId,
                        otp = otp
                    )
                )
            }
        }){
            is Response.Error -> result
            is Response.Result -> {
                sharedPreferences.edit {
                    putString(KEY_TOKEN, result.data.token)
                    apply()
                }
                result
            }
        }
    }

    suspend fun setForgotPassword(
        newPassword:String
    ): Response<String>{
        val sessionId = sharedPreferences.getString(KEY_SESSION_ID, null)
            ?: return Response.Error("Sesi tidak ditemukan sudah habis", 401)

        return when(val result = safeApiCall<String> {
            httpClient.post("/api/auth/forgot-password/set-password"){
                setBody(SetForgotPasswordRequest(
                    password = newPassword,
                    sessionId = sessionId
                ))
            }
        }){
            is Response.Error -> result
            is Response.Result -> {
                sharedPreferences.edit {
                    remove(KEY_SESSION_ID)
                    remove(KEY_TOKEN)
                    remove(KEY_IS_LOGGED_IN)
                    apply()
                }
                result
            }
        }
    }
    //end password

}