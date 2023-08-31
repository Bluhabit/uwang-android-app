/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.task.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class SubTaskResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("assign")
    val assign: UserResponse? = null,
    @SerializedName("name")
    val name: String,
    @SerializedName("done")
    val done: Boolean,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("deleted")
    val deleted: Boolean
)