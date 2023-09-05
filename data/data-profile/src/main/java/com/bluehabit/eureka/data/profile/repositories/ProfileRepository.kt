/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.profile.repositories

import com.bluehabit.eureka.data.common.Response
import com.bluehabit.eureka.data.common.safeApiCall
import com.bluehabit.eureka.data.persistence.SharedPref
import com.bluehabit.eureka.data.profile.datasource.ProfileConstant
import com.bluehabit.eureka.data.profile.datasource.remote.ProfileApi
import com.bluehabit.eureka.data.profile.datasource.remote.response.ProfileUserResponse
import io.ktor.client.HttpClient
import io.ktor.client.plugins.onUpload
import io.ktor.client.plugins.resources.get
import io.ktor.client.plugins.resources.post
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import java.io.File
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val pref: SharedPref
) {
    suspend fun getProfileDetail(): Response<ProfileUserResponse> =
        when (val result = safeApiCall<ProfileUserResponse> {
            httpClient.get(ProfileApi.GetDetailProfile)
        }) {
            is Response.Result -> {
                pref.apply {
                    setPersistData(ProfileConstant.USER_PROFILE_URL, result.data.userInfo.photoProfile.orEmpty())
                    setPersistData(ProfileConstant.USER_FULL_NAME, result.data.userInfo.fullName.orEmpty())
                }
                result
            }

            else -> {
                result
            }
        }

    suspend fun uploadProfilePicture(
        file: File
    ): Response<ProfileUserResponse> =
        when (val result = safeApiCall<ProfileUserResponse> {
            httpClient.post(ProfileApi.UploadProfilePicture) {
                setBody(
                    MultiPartFormDataContent(
                        formData {
                            append("profilePicture", file.readBytes(), Headers.build {
                                append(HttpHeaders.ContentType, "image/png")
                            })
                        },
                        boundary = "WebAppBoundary"
                    )
                )
                onUpload { bytesSentTotal, contentLength ->
                    //tracking progress
                }
            }
        }) {
            is Response.Result -> {
                pref.apply {
                    setPersistData(ProfileConstant.USER_PROFILE_URL, result.data.userInfo.photoProfile.orEmpty())
                }
                result
            }

            else -> {
                result
            }
        }
}