/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.data.authentication.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class SignInGoogleResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("credential")
    val credential: String
)
