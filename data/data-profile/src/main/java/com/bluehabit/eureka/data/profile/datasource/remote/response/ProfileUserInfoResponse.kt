/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.profile.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class ProfileUserInfoResponse(
    @SerializedName("fullName")
    val fullName: String? = null,
    val photoProfile: String? = null,
)