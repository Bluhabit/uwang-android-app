/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.remote.dummy

import com.bluehabit.budgetku.data.R
import com.bluehabit.budgetku.data.model.ExpensesBudgetCategory
import java.math.BigDecimal

val dummyBudgetCategory:List<ExpensesBudgetCategory> = listOf(
    ExpensesBudgetCategory(
        categoryName = "Makan & Minuman",
        categoryImage = R.drawable.dummy_category_makan_minum,
        amount = BigDecimal(1_500_000),
        usage = "50%"
    ),
    ExpensesBudgetCategory(
        categoryName = "Tagihan",
        categoryImage = R.drawable.dummy_category_tagihan,
        amount = BigDecimal(500_000),
        usage = "10%"
    ),
    ExpensesBudgetCategory(
        categoryName = "Transportasi",
        categoryImage = R.drawable.dummy_category_kendaraan,
        amount = BigDecimal(750_000),
        usage = "13%"
    )
)