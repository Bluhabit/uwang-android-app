/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.model.transaction

import java.math.BigDecimal
import java.time.LocalDate

data class TransactionModel(
    val transactionName:String,
    val transactionAccountName:String,
    val transactionDate:LocalDate,
    val transactionCategory:String,
    val isTransactionExpenses:Boolean,
    val transactionAmount:BigDecimal
)
