/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.entity

data class UserModel(
    val id: String,
    val fullName: String,
    val email: String,
    val phoneNumber: String,
    val dateOfBirth: String,
    val countryCode: String,
    val authProvider: String,
    val authTokenProvider: String,
    val status: String,
    val createdAt: Long,
    val updatedAt: Long,
)
