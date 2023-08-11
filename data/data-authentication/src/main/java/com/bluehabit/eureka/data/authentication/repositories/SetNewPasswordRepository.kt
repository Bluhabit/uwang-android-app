/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.repositories

import com.bluehabit.eureka.data.authentication.AuthConstant
import com.bluehabit.eureka.data.authentication.datasource.remote.AuthApi
import com.bluehabit.eureka.data.authentication.datasource.remote.request.SetNewPasswordRequest
import com.bluehabit.eureka.data.authentication.datasource.remote.response.SetNewPasswordResponse
import com.bluehabit.eureka.data.common.Response
import com.bluehabit.eureka.data.common.safeApiCall
import com.bluehabit.eureka.data.persistence.SharedPref
import io.ktor.client.HttpClient
import io.ktor.client.plugins.resources.post
import io.ktor.client.request.headers
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.http.append
import okhttp3.internal.http2.Header
import javax.inject.Inject

class SetNewPasswordRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val pref: SharedPref
) {
    suspend fun execute(password: String):Response<SetNewPasswordResponse>{
        val result = safeApiCall<SetNewPasswordResponse> {
            httpClient.post(AuthApi.SetNewPassword()) {
                headers {
                    append(HttpHeaders.Accept, "text/html")
                    append(HttpHeaders.Authorization, "abc123")
                    append(HttpHeaders.UserAgent, "ktor client")
                    append("4adf-3ed", pref.getPersistData(AuthConstant.SESSION_TOKEN_RESET_PW).orEmpty())
                }
                setBody(
                    SetNewPasswordRequest(
                        password
                    )
                )
            }
        }
       return result
    }
}

