/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class RequestResetPasswordResponse(
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("data")
    val data: Any,
    @SerializedName("message")
    val message: String,
)