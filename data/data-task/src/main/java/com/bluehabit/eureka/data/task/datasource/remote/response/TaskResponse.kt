/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.task.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class TaskResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("createdBy")
    val createdBy: UserResponse,
    @SerializedName("channel")
    val channel: ChannelResponse? = null,
    @SerializedName("assign")
    val assign: UserResponse? = null,
    @SerializedName("priority")
    val priority: PriorityTaskResponse? = null,
    @SerializedName("status")
    val status: StatusTaskResponse? = null,
    @SerializedName("attachment")
    val attachments: Any? = null,
    @SerializedName("subtask")
    val subtasks: List<SubTaskResponse>? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("taskStart")
    val taskStart: String? = null,
    @SerializedName("taskEnd")
    val taskEnd: String? = null,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("deleted")
    val deleted: Boolean,
    @SerializedName("publish")
    val publish: Boolean
)
