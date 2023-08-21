/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.repositories

import com.bluehabit.eureka.data.authentication.AuthConstant
import com.bluehabit.eureka.data.authentication.datasource.remote.AuthApi
import com.bluehabit.eureka.data.authentication.datasource.remote.request.LinkConfirmationRequest
import com.bluehabit.eureka.data.authentication.datasource.remote.request.RequestResetPasswordRequest
import com.bluehabit.eureka.data.authentication.datasource.remote.request.ResetPasswordRequest
import com.bluehabit.eureka.data.authentication.datasource.remote.response.LinkConfirmationResponse
import com.bluehabit.eureka.data.common.Response
import com.bluehabit.eureka.data.common.safeApiCall
import com.bluehabit.eureka.data.model.BaseResponse
import com.bluehabit.eureka.data.persistence.SharedPref
import io.ktor.client.HttpClient
import io.ktor.client.plugins.resources.post
import io.ktor.client.request.headers
import io.ktor.client.request.setBody
import javax.inject.Inject

class ResetPasswordRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val pref: SharedPref
) {
    suspend fun requestResetPassword(
        email: String,
    ): Response<BaseResponse<Any>> = safeApiCall {
        httpClient.post(AuthApi.RequestResetPassword()) {
            setBody(RequestResetPasswordRequest(email))
        }
    }

    suspend fun linkConfirmation(
        token: String
    ): Response<BaseResponse<LinkConfirmationResponse>> =
        when (val result = safeApiCall<BaseResponse<LinkConfirmationResponse>> {
            httpClient.post(AuthApi.LinkConfirmation()) {
                setBody(LinkConfirmationRequest(token))
            }
        }) {
            is Response.Result -> {
                pref.setPersistData(AuthConstant.SESSION_TOKEN_RESET_PASSWORD, result.data.data.sessionId)
                result
            }

            else -> {
                result
            }
        }

    suspend fun resetPassword(
        newPassword: String
    ): Response<BaseResponse<Any>> = safeApiCall {
        httpClient.post(AuthApi.RequestResetPassword) {
            headers {
                append(
                    name = AuthConstant.TOKEN_RESET_PASSWORD,
                    value = pref.getPersistData(AuthConstant.SESSION_TOKEN_RESET_PASSWORD).orEmpty()
                )
            }
            setBody(ResetPasswordRequest(newPassword))
        }
    }

}