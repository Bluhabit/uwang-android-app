/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.data.authentication.repositories

import android.graphics.Bitmap
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.CompleteProfileRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.ForgotPasswordRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.ResendOtpForgotPasswordRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.ResendOtpSignInBasicRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.ResendOtpSignUpBasicRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.SetForgotPasswordRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.SignInBasicRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.SignInGoogleRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.SignUpBasicRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.VerifyOtpForgotPasswordRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.VerifyOtpSignInRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.VerifyOtpSignUpRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.response.ForgotPasswordResponse
import com.bluhabit.blu.android.data.authentication.datasource.remote.response.ResendOtpForgotPasswordResponse
import com.bluhabit.blu.android.data.authentication.datasource.remote.response.ResendOtpSignInBasicResponse
import com.bluhabit.blu.android.data.authentication.datasource.remote.response.ResendOtpSignUpBasicResponse
import com.bluhabit.blu.android.data.authentication.datasource.remote.response.SignInBasicResponse
import com.bluhabit.blu.android.data.authentication.datasource.remote.response.SignInGoogleResponse
import com.bluhabit.blu.android.data.authentication.datasource.remote.response.SignUpBasicResponse
import com.bluhabit.blu.android.data.authentication.datasource.remote.response.UserCredentialResponse
import com.bluhabit.blu.data.common.Response
import com.bluhabit.blu.data.common.safeApiCall
import com.bluhabit.blu.data.persistence.SharedPref
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val sharedPref: SharedPref
) {
    companion object {
        const val KEY_SESSION_ID = "d0ca-fc40"
        const val KEY_USER_ID = "fcd0-acbd"

    }

    //session
    fun isLoggedIn(): Boolean {
        return sharedPref.getIsLoggedIn()
    }
    //end session

    //sign in region
    suspend fun signInBasic(email: String, password: String): Response<String> {
        return when (val result = safeApiCall<String> {
            httpClient.post("auth/v1/sign-in-basic") {
                setBody(SignInBasicRequest(email = email, password = password))
            }
        }) {
            is Response.Error -> result
            is Response.Result -> {
                if (result.data.isNotEmpty()) {
                    sharedPref.setPersistData(KEY_SESSION_ID, result.data)
                }
                result
            }
        }
    }

    suspend fun verifyOtpSignInBasic(
        otp: String
    ): Response<SignInBasicResponse> {
        val sessionId = sharedPref.getPersistData(KEY_SESSION_ID)
            ?: return Response.Error("Sesi tidak ditemukan sudah habis", 401)
        return when (val result = safeApiCall<SignInBasicResponse> {
            httpClient.post("auth/v1/sign-in-basic/verify-otp") {
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
                sharedPref.removePersistData(KEY_SESSION_ID)
                result
            }
        }
    }

    suspend fun resendOtpSignInBasic(): Response<ResendOtpSignInBasicResponse> {
        val sessionId = sharedPref.getPersistData(KEY_SESSION_ID)
            ?: return Response.Error("Sesi tidak ditemukan sudah habis", 401)
        return safeApiCall<ResendOtpSignInBasicResponse> {
            httpClient.post("auth/v1/sign-in-basic/resend-otp") {
                setBody(
                    ResendOtpSignInBasicRequest(
                        sessionId = sessionId
                    )
                )
            }
        }
    }

    suspend fun signInGoogle(
        token: String
    ): Response<SignInGoogleResponse> {
        return when (val result = safeApiCall<SignInGoogleResponse> {
            httpClient.post("auth/v1/sign-in-google") {
                setBody(SignInGoogleRequest(token))
            }
        }) {
            is Response.Error -> result
            is Response.Result -> {
                //set session to share pref
                sharedPref.removePersistData(KEY_USER_ID)
                sharedPref.setPersistData(KEY_USER_ID, result.data.credential.id)
                sharedPref.setToken(result.data.token)
                sharedPref.setIsLoggedIn(true)
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
            httpClient.post("auth/v1/sign-up-basic") {
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
                    sharedPref.setPersistData(KEY_SESSION_ID, result.data)
                }
                result
            }
        }
    }

    suspend fun verifyOtpSignUpBasic(otp: String): Response<SignUpBasicResponse> {
        val sessionId = sharedPref.getPersistData(KEY_SESSION_ID)
            ?: return Response.Error("Sesi tidak ditemukan sudah habis", 401)

        return when (val result = safeApiCall<SignUpBasicResponse> {
            httpClient.post("auth/v1/sign-up-basic/verify-otp") {
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
                sharedPref.removePersistData(KEY_USER_ID)
                sharedPref.setPersistData(KEY_USER_ID, result.data.credential.id)
                sharedPref.setToken(result.data.token)
                sharedPref.setIsLoggedIn(true)
                result
            }
        }
    }

    suspend fun resendOtpSignUpBasic(): Response<ResendOtpSignUpBasicResponse> {
        val sessionId = sharedPref.getPersistData(KEY_SESSION_ID)
            ?: return Response.Error("Sesi tidak ditemukan sudah habis", 401)

        return safeApiCall {
            httpClient.post("auth/v1/sign-up-basic/resend-otp") {
                setBody(
                    ResendOtpSignUpBasicRequest(
                        sessionId = sessionId
                    )
                )
            }
        }
    }

    //end sign up
    //forgot password
    suspend fun forgotPassword(email: String): Response<String> {
        return when (val result = safeApiCall<String> {
            httpClient.post("auth/v1/forgot-password") {
                setBody(ForgotPasswordRequest(email = email))
            }
        }) {
            is Response.Error -> result
            is Response.Result -> {
                if (result.data.isNotEmpty()) {
                    sharedPref.setPersistData(KEY_SESSION_ID, result.data)
                }
                result
            }
        }
    }

    suspend fun verifyOtpForgotPassword(
        otp: String
    ): Response<ForgotPasswordResponse> {
        val sessionId = sharedPref.getPersistData(KEY_SESSION_ID)
            ?: return Response.Error("Sesi tidak ditemukan sudah habis", 401)

        return when (val result = safeApiCall<ForgotPasswordResponse> {
            httpClient.post("auth/v1/forgot-password/verify-otp") {
                setBody(
                    VerifyOtpForgotPasswordRequest(
                        sessionId = sessionId,
                        otp = otp
                    )
                )
            }
        }) {
            is Response.Error -> result
            is Response.Result -> {
                sharedPref.setToken(result.data.token)
                sharedPref.setPersistData(KEY_USER_ID, result.data.credential.id)
                result
            }
        }
    }

    suspend fun resentOtpForgotPassword(): Response<ResendOtpForgotPasswordResponse> {
        val sessionId = sharedPref.getPersistData(KEY_SESSION_ID)
            ?: return Response.Error("Sesi tidak ditemukan sudah habis", 401)

        return safeApiCall {
            httpClient.post("auth/v1/forgot-password/resend-otp") {
                setBody(
                    ResendOtpForgotPasswordRequest(
                        sessionId = sessionId
                    )
                )
            }
        }
    }

    suspend fun setForgotPassword(
        newPassword: String
    ): Response<String> {
        val sessionId = sharedPref.getPersistData(KEY_SESSION_ID)
            ?: return Response.Error("Sesi tidak ditemukan sudah habis", 401)

        return when (val result = safeApiCall<String> {
            httpClient.post("auth/v1/forgot-password/set-password") {
                setBody(
                    SetForgotPasswordRequest(
                        password = newPassword,
                        sessionId = sessionId
                    )
                )
            }
        }) {
            is Response.Error -> result
            is Response.Result -> {
                sharedPref.removePersistData(KEY_SESSION_ID)
                sharedPref.clearAllSession()
                result
            }
        }
    }

    //end password
    //compete profile
    suspend fun uploadAvatar(
        avatar: Bitmap
    ): Response<String> {

        return Response.Error("", 10)
    }

    suspend fun updateProfile(
        avatar: String,
        username: String,
        dateOfBirth: String,
        personalPreferences: List<String>
    ): Response<UserCredentialResponse> {

        return safeApiCall {
            httpClient.post("auth/v1/complete-profile") {
                setBody(
                    CompleteProfileRequest(
                        avatar = avatar,
                        username = username,
                        dateOfBirth = dateOfBirth,
                        personalPreferences = personalPreferences
                    )
                )
            }
        }
    }
    //end

}