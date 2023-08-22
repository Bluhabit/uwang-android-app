/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.datasource.remote.request

import com.google.gson.annotations.SerializedName

data class ResetPasswordRequest(
    @SerializedName("newPassword")
    val newPassword: String
)