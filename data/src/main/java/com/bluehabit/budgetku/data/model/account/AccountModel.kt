/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.model.account

import java.math.BigDecimal

data class AccountModel(
    val id:String,
    val icon:Int,
    val accountName:String,
    val accountBalance:BigDecimal,
    val connectedSaving:AccountSavingModel?=null
)
