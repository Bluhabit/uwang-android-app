/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.model.account

import java.time.LocalDateTime

data class AccountSavingModel(
    val icon:Int,
    val accountIcon:Int,
    val savingName:String,
    val targetBalance:String,
    val totalBalance:String,
    val target:String,
    val progress:Float
)
