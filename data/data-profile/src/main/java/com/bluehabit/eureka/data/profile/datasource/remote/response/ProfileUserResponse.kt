/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.profile.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class ProfileUserResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("userInfo")
    val userInfo: ProfileUserInfoResponse,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
)