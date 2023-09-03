/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.task.datasource.remote.request

import com.google.gson.annotations.SerializedName

data class SubtaskRequest(
    @SerializedName("subTaskName")
    val subTaskName: String,
    @SerializedName("done")
    val done: Boolean
)
