/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("data")
    val data: T,
    @SerializedName("message")
    val message: String
)

data class BaseResponseError<T>(
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("data")
    val data: T,
    @SerializedName("message")
    val message: String,
    @SerializedName("errorField")
    val errorField: List<Map<String, String>>,
)