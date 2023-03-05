/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.model

data class SignUpEmailRequest(
    val fullName:String,
    val email: String,
    val password: String,
)
