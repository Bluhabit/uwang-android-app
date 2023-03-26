/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.model

data class BaseResponse<T>(
    val code: Int,
    val data: T,
    val message: String,
    val token: String,
)