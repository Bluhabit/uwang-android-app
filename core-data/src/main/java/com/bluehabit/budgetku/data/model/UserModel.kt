/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.model

data class UserModel(
    val fullName:String,
    val email:String,
    val password:String,
    val dateOfBirth:String,
    val gender:String,
    val pin:String
)
