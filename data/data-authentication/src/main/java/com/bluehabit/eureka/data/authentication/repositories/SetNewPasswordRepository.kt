/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.repositories

import com.bluehabit.eureka.data.authentication.datasource.remote.AuthApi
import com.bluehabit.eureka.data.authentication.datasource.remote.request.SetNewPasswordRequest
import com.bluehabit.eureka.data.authentication.datasource.remote.request.SignInWithEmailRequest
import com.bluehabit.eureka.data.authentication.datasource.remote.response.SetNewPasswordResponse
import com.bluehabit.eureka.data.authentication.datasource.remote.response.SignInResponse
import com.bluehabit.eureka.data.common.Response
import com.bluehabit.eureka.data.common.safeApiCall
import com.bluehabit.eureka.data.persistence.SharedPref
import io.ktor.client.HttpClient
import io.ktor.client.plugins.resources.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class SetNewPasswordRepository @Inject constructor(
    private val httpClient: HttpClient
) {
    suspend fun execute(password: String):Response<SetNewPasswordResponse>{
        val result = safeApiCall<SetNewPasswordResponse> {
            httpClient.post(AuthApi.SetNewPassword()) {
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

