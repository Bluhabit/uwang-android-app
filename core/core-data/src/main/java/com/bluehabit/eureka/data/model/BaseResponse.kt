/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.model

data class BaseResponse<T>(
    val statusCode: Int,
    val data: T,
    val message: String
)