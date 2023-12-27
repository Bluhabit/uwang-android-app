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
import com.bluhabit.blu.android.data.authentication.datasource.remote.response.UserCredentialResponse
import com.bluhabit.blu.data.common.Response
import com.bluhabit.blu.data.common.safeApiCall
import com.bluhabit.blu.data.persistence.SharedPref
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

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
            httpClient.post("/api/auth/sign-in-basic") {
                setBody(SignInBasicRequest(email = email, password = password))
            }
        }) {
            is Response.Error -> result
            is Response.Result -> {
                if (result.data.isNotEmpty()) {
                    sharedPref.setPersistData(KEY_SESSION_ID,result.data)
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
                sharedPref.removePersistData(KEY_SESSION_ID)
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
                sharedPref.removePersistData(KEY_USER_ID)
                sharedPref.setPersistData(KEY_USER_ID,result.data.credential.id)
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
                    sharedPref.setPersistData(KEY_SESSION_ID,result.data)
                }
                result
            }
        }
    }

    suspend fun verifyOtpSignUpBasic(otp: String): Response<SignUpBasicResponse> {
        val sessionId = sharedPref.getPersistData(KEY_SESSION_ID)
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
                sharedPref.removePersistData(KEY_USER_ID)
                sharedPref.setPersistData(KEY_USER_ID,result.data.credential.id)
                sharedPref.setToken(result.data.token)
                sharedPref.setIsLoggedIn(true)
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
                    sharedPref.setPersistData(KEY_SESSION_ID,result.data)
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
            httpClient.post("/api/forgot-password/verify-otp") {
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
                sharedPref.setPersistData(KEY_USER_ID,result.data.credential.id)
                result
            }
        }
    }

    suspend fun setForgotPassword(
        newPassword: String
    ): Response<String> {
        val sessionId = sharedPref.getPersistData(KEY_SESSION_ID)
            ?: return Response.Error("Sesi tidak ditemukan sudah habis", 401)

        return when (val result = safeApiCall<String> {
            httpClient.post("/api/auth/forgot-password/set-password") {
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

        val userId = sharedPref.getPersistData(KEY_USER_ID) ?: return Response.Error("Sesi sudah berakhir", 401)


        val storageRef = Firebase.storage.reference
        val avatarRef = storageRef.child("avatar")
            .child(userId.plus(".png"))

        val baos = ByteArrayOutputStream()
        avatar.compress(Bitmap.CompressFormat.PNG,100,baos)
        val data = baos.toByteArray()

        var uploadTask = avatarRef.putBytes(data)

        val result = uploadTask.continueWithTask {
            if (!it.isSuccessful) {
                it.exception?.let { throw it }
            }
            avatarRef.downloadUrl
        }.await()

        return Response.Result(result.toString())
    }

    suspend fun updateProfile(
        avatar: String,
        username: String,
        dateOfBirth: String,
        personalPreferences: List<String>
    ): Response<UserCredentialResponse> {

        return safeApiCall {
            httpClient.post("/api/user/complete-profile") {
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