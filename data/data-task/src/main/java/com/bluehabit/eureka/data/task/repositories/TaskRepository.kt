/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.task.repositories

import com.bluehabit.eureka.data.common.Response
import com.bluehabit.eureka.data.common.safeApiCall
import com.bluehabit.eureka.data.model.BasePagingResponse
import com.bluehabit.eureka.data.persistence.SharedPref
import com.bluehabit.eureka.data.task.datasource.TaskConstant
import com.bluehabit.eureka.data.task.datasource.remote.TaskApi
import com.bluehabit.eureka.data.task.datasource.remote.request.PublishTaskRequest
import com.bluehabit.eureka.data.task.datasource.remote.response.AttachmentResponse
import com.bluehabit.eureka.data.task.datasource.remote.response.PriorityTaskResponse
import com.bluehabit.eureka.data.task.datasource.remote.response.StatusTaskResponse
import com.bluehabit.eureka.data.task.datasource.remote.response.TaskResponse
import io.ktor.client.HttpClient
import io.ktor.client.plugins.onUpload
import io.ktor.client.plugins.resources.get
import io.ktor.client.plugins.resources.post
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import java.io.File
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val pref: SharedPref,
    private val httpClient: HttpClient
) {
    suspend fun getListPriority(page: Int = 0): Response<BasePagingResponse<PriorityTaskResponse>> {
        return safeApiCall { httpClient.get(TaskApi.GetListPriority(page = page)) }
    }

    suspend fun getListStatus(page: Int = 0): Response<BasePagingResponse<StatusTaskResponse>> {
        return safeApiCall { httpClient.get(TaskApi.GetListStatus(page = page)) }
    }

    suspend fun getListTask(page: Int = 0): Response<BasePagingResponse<TaskResponse>> {
        return safeApiCall { httpClient.get(TaskApi.GetListTask(page = page)) }
    }

    suspend fun createTemporaryTask(): Response<TaskResponse> =
        when (val response = safeApiCall<TaskResponse> { httpClient.post(TaskApi.CreateTemporaryTask()) {} }) {
            is Response.Result -> {
                val taskId = response.data.id
                pref.setPersistData(TaskConstant.CURRENT_TASK_ID, taskId)
                response
            }

            else -> response
        }


    suspend fun uploadAttachment(
        file: File
    ): Response<AttachmentResponse> {
        val currentTaskId = pref.getPersistData(TaskConstant.CURRENT_TASK_ID)
        return when (val response = safeApiCall<AttachmentResponse> {
            httpClient.post(TaskApi.UploadAttachment()) {
                setBody(
                    MultiPartFormDataContent(
                        formData {
                            append("taskId", currentTaskId.orEmpty())
                            append("file", file.readBytes(), Headers.build {
                                append(HttpHeaders.ContentType, "image/png")
                                append(HttpHeaders.ContentDisposition, "filename=\"${currentTaskId}.png\"")
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
                val taskId = response.data.id
                pref.setPersistData(TaskConstant.CURRENT_TASK_ID, taskId)
                response
            }

            else -> response
        }
    }

    suspend fun publishTask(request: PublishTaskRequest): Response<TaskResponse> {
        val currentTaskId = pref.getPersistData(TaskConstant.CURRENT_TASK_ID).orEmpty()
        return when (val response = safeApiCall<TaskResponse> {
            httpClient.post(TaskApi.Publish()) {
                setBody(request.copy(taskId = currentTaskId))
            }
        }) {
            is Response.Result -> {
                pref.removePersistData(TaskConstant.CURRENT_TASK_ID)
                response
            }

            else -> response
        }
    }
}