/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.data.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("data")
    val data: T,
    @SerializedName("message")
    val message: String
)

data class BaseResponseError<T>(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("data")
    val data: T,
    @SerializedName("message")
    val message: String,
    @SerializedName("error_field")
    val errorField: List<Map<String, String>>,
)