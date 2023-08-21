/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class UserCredentialResponse(
    @SerializedName("email")
    val email: String,
    @SerializedName("userInfo")
    val userInfo: UserInfoResponse,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)

