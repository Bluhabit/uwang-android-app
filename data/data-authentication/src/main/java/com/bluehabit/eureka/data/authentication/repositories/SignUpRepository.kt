/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.repositories

import com.bluehabit.eureka.data.authentication.AuthConstant.SESSION_OTP_ID
import com.bluehabit.eureka.data.authentication.datasource.remote.AuthApi
import com.bluehabit.eureka.data.authentication.datasource.remote.request.CompleteProfileRequest
import com.bluehabit.eureka.data.authentication.datasource.remote.request.OtpConfirmationRequest
import com.bluehabit.eureka.data.authentication.datasource.remote.request.SignUpWithEmailRequest
import com.bluehabit.eureka.data.authentication.datasource.remote.response.CompleteProfileResponse
import com.bluehabit.eureka.data.authentication.datasource.remote.response.OtpConfirmationResponse
import com.bluehabit.eureka.data.authentication.datasource.remote.response.SignUpWithEmailResponse
import com.bluehabit.eureka.data.common.Response
import com.bluehabit.eureka.data.common.safeApiCall
import com.bluehabit.eureka.data.persistence.SharedPref
import io.ktor.client.HttpClient
import io.ktor.client.plugins.resources.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class SignUpRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val pref: SharedPref
) {
    suspend fun signUpWIthEmail(email: String): Response<SignUpWithEmailResponse> =
        when (val result = safeApiCall<SignUpWithEmailResponse> {
            httpClient.post(AuthApi.SignUpWithEmail()) {
                setBody(SignUpWithEmailRequest(email))
            }
        }) {
            is Response.Result -> {
                pref.setPersistData(SESSION_OTP_ID, result.data.sessionId)
                result
            }

            else -> result
        }

    suspend fun otpConfirmation(otp: String): Response<OtpConfirmationResponse> {
        return when (val response = safeApiCall<OtpConfirmationResponse> {
            httpClient.post(AuthApi.OtpConfirmation()) {
                setBody(OtpConfirmationRequest(otp = otp, sessionId = pref.getPersistData(SESSION_OTP_ID).orEmpty()))
            }
        }) {
            is Response.Result -> {
                pref.setPersistData(SESSION_OTP_ID, response.data.sessionId)
                response
            }

            else -> response
        }
    }

    suspend fun completeProfile(fullName: String, password: String): Response<CompleteProfileResponse> {
        return when (val response = safeApiCall<CompleteProfileResponse> {
            httpClient.post(AuthApi.CompleteProfile()) {
                setBody(
                    CompleteProfileRequest(
                        pref.getPersistData(SESSION_OTP_ID).orEmpty(),
                        fullName,
                        password
                    )
                )
            }
        }) {
            is Response.Result -> {
                pref.removePersistData(SESSION_OTP_ID)
                response
            }

            else -> response
        }
    }
}