/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.model

data class CommentModel(
    val fullName:String,
    val avatar:Int,
    val time:String,
    val body:String,
    val likes:Int
)
