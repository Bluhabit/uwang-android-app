/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.data.authentication.repositories

import android.content.Context
import android.graphics.Bitmap
import com.bluhabit.blu.android.data.authentication.DataConstant.KEY_PROFILE_PICTURE
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.PersonalizeLevelRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.PersonalizeProfilePictureRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.PersonalizeTopicsRequest
import com.bluhabit.blu.android.data.authentication.datasource.remote.request.PersonalizeUsernameRequest
import com.bluhabit.blu.data.common.Response
import com.bluhabit.blu.data.common.safeApiCall
import com.bluhabit.blu.data.persistence.SharedPref
import com.bluhabit.core.ui.ext.toFile
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.HttpClient
import io.ktor.client.plugins.onUpload
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val sharedPref: SharedPref,
    @ApplicationContext private val context: Context
) {
    suspend fun updateUsername(
        username: String
    ): Response<Any> {
        return safeApiCall {
            httpClient.post("account/v1/update-profile-username") {
                setBody(PersonalizeUsernameRequest(username = username))
            }
        }
    }

    suspend fun updateProfilePicture(
        bitmap: Bitmap
    ): Response<Any> {
        val file = bitmap.toFile(context = context)
            ?: return Response.Error("File tidak ditemukan", 400)
        return safeApiCall {
            httpClient.post("storage/v1/upload-profile-picture") {
                setBody(
                    MultiPartFormDataContent(
                        formData {
                            append("file", file.readBytes(), Headers.build {
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
        }
    }

    suspend fun updateProfilePicture(): Response<Any> {
        val url = sharedPref.getPersistData(KEY_PROFILE_PICTURE) ?: ""
        return safeApiCall {
            httpClient.post("account/v1/update-profile-picture") {
                setBody(PersonalizeProfilePictureRequest(url = url))
            }
        }
    }

    suspend fun updateTopicInterest(
        topics: List<String>
    ): Response<Any> {
        return safeApiCall {
            httpClient.post("account/v1/update-profile-topics") {
                setBody(PersonalizeTopicsRequest(topics = topics.joinToString(",")))
            }
        }
    }

    suspend fun updateLevel(
        level: String
    ): Response<Any> {
        return safeApiCall {
            httpClient.post("account/v1/update-profile-level") {
                setBody(PersonalizeLevelRequest(level = level))
            }
        }
    }
}