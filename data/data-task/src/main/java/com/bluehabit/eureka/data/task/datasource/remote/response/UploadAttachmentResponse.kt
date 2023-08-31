/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.task.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class UploadAttachmentResponse(
    @SerializedName("")
    val id: String,
    val name: String,
    val type: String,
    val mimeType: String,
    val createdAt: String,
    val updatedAt: String,
    val deleted: Boolean,
)
