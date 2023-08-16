/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.datasource.remote.request

import com.google.gson.annotations.SerializedName

data class SignInWithEmailRequest(
    @SerializedName("email")
    val email:String,
    @SerializedName("password")
    val password:String
)
