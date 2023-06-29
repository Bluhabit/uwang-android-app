/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.model.transaction

import java.math.BigDecimal

data class TransactionModel(
    val transactionId:String,
    val transactionName:String,
    val transactionAccountName:String,
    val transactionDate:String,
    val transactionCategory:String,
    val isTransactionExpenses:Boolean,
    val transactionAmount:BigDecimal,
    val transactionIcon:Int,
    val transactionAccountIcon:Int
)
