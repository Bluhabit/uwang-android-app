/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.data.authentication.datasource.remote.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CompleteProfileRequest(
    @SerializedName("username")
    val username: String,
    @SerializedName("avatar")
    val avatar:String,
    @SerializedName("date_of_birth")
    val dateOfBirth:String,
    @SerializedName("personal_preferences")
    val personalPreferences:List<String>
)
