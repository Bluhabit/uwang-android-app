/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.remote.dummy

import com.bluehabit.budgetku.data.R
import com.bluehabit.budgetku.data.model.account.AccountModel
import java.math.BigDecimal

val dummyAccountsHome : List<AccountModel> = listOf(
    AccountModel(
        id="001",
        icon = R.drawable.dummy_bank_bsi,
        accountName = "Bank BSI",
        accountBalance = BigDecimal(1_500_000)
    ),
    AccountModel(
        id="002",
        icon = R.drawable.dummy_bank_mandiri,
        accountName = "Bank Mandiri",
        accountBalance = BigDecimal(1_000_000)
    ),
    AccountModel(
        id="003",
        icon = R.drawable.dummy_bank_jago,
        accountName = "Bank Jago",
        accountBalance = BigDecimal(15_000_000)
    ),
    AccountModel(
        id="004",
        icon = R.drawable.dummy_category_dana_darurat,
        accountName = "Cash",
        accountBalance = BigDecimal(500_000)
    ),
)