/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.remote.dummy

import com.bluehabit.budgetku.data.model.transaction.TransactionModel
import java.math.BigDecimal

val dummyTransactions :List<TransactionModel> = listOf(
    TransactionModel(
        transactionId = "002",
        transactionName = "Kopi Starbucks",
        isTransactionExpenses = true,
        transactionAmount = BigDecimal(94_000),
        transactionDate = "22 May 2023",
        transactionAccountName = "Cash",
        transactionCategory = "Makanan & Minuman",
        transactionIcon =  com.bluehabit.budgetku.data.R.drawable.dummy_category_coffee_shop,
        transactionAccountIcon =  com.bluehabit.budgetku.data.R.drawable.dummy_category_dana_darurat
    ),
    TransactionModel(
        transactionId = "003",
        transactionName = "Bayar Listrik",
        isTransactionExpenses = true,
        transactionAmount = BigDecimal(120_000),
        transactionDate = "21 May 2023",
        transactionAccountName = "Bank BSI",
        transactionCategory = "Tagihan",
        transactionIcon =  com.bluehabit.budgetku.data.R.drawable.dummy_category_tagihan,
        transactionAccountIcon =  com.bluehabit.budgetku.data.R.drawable.dummy_bank_bsi
    ),
    TransactionModel(
        transactionId = "004",
        transactionName = "Grabcar",
        isTransactionExpenses = true,
        transactionAmount = BigDecimal(60_000),
        transactionDate = "20 May 2023",
        transactionAccountName = "Bank Mandiri",
        transactionCategory = "Ojek Online",
        transactionIcon =  com.bluehabit.budgetku.data.R.drawable.dummy_category_kendaraan,
        transactionAccountIcon =  com.bluehabit.budgetku.data.R.drawable.dummy_bank_mandiri
    )
)