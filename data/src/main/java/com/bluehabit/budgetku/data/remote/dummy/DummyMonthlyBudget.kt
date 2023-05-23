/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.remote.dummy

import com.bluehabit.budgetku.data.R
import com.bluehabit.budgetku.data.model.LatestTransactionMonthly
import java.math.BigDecimal

val dummyLatestMonthlyBudget : List<LatestTransactionMonthly> = listOf(
    LatestTransactionMonthly(
        transactionName = "Makanan & Minuman",
        usedAmount = BigDecimal(1_156_000),
        icon = R.drawable.dummy_category_makan_minum
    ),
    LatestTransactionMonthly(
        transactionName = "Kebutuhan Rumah",
        usedAmount = BigDecimal(2_485_000),
        icon = R.drawable.dummy_category_kebutuhanrumah
    ),
    LatestTransactionMonthly(
        transactionName = "Tagihan & Utilitas",
        usedAmount = BigDecimal(1_359_000),
        icon = R.drawable.dummy_category_tagihan
    )
)