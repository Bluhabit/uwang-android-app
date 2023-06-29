/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.model

import java.math.BigDecimal

data class ExpensesBudgetCategory(
    val categoryName:String="",
    val categoryImage:Int=0,
    val usage:String="",
    val amount: BigDecimal = BigDecimal.ZERO,
)
