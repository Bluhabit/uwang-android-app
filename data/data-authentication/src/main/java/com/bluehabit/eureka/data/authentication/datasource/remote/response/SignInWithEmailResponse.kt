/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.datasource.remote.response

data class SignInResponse(
    val token: String,
    val credential: Credential
)

data class Credential(
    val userId: String,
    val userName: String,
    val userEmail: String,
    val createdAt: String,
    val updatedAt: String
)
