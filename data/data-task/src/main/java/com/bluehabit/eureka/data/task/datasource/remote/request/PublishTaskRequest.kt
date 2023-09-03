/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.task.datasource.remote.request

import com.google.gson.annotations.SerializedName

data class PublishTaskRequest(
    @SerializedName("taskId")
    val taskId: String,
    @SerializedName("taskDescription")
    val taskDescription: String,
    @SerializedName("taskName")
    val taskName: String,
    @SerializedName("subtask")
    val subtask: List<SubtaskRequest>,
    @SerializedName("priorityId")
    val priorityId: String? = null
)
