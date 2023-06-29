/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.model

data class ChallengeModel(
    val title:String,
    val message:String,
    val progress:Float,
    val totalPoints:Int,
    val targetPoints:Int,
    val color: Long,
    val textColor: Long,
    val image:Int
)