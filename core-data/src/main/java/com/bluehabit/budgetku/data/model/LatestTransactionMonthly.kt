/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.model

import java.math.BigDecimal

data class LatestTransactionMonthly(
    val transactionName: String = "",
    val usedAmount: BigDecimal = BigDecimal.ZERO,
    val icon:Int
)