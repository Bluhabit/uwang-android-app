/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.remote.dummy

import com.bluehabit.budgetku.data.R
import com.bluehabit.budgetku.data.model.account.AccountSavingModel
import java.time.LocalDateTime

val dummyAcountSaving: List<AccountSavingModel> = listOf(
    AccountSavingModel(
        icon = R.drawable.dummy_category_kendaraan,
        accountIcon = R.drawable.dummy_bank_jago,
        target = "14 Januari 2023",
        savingName = "Motor Baru",
        targetBalance = "Rp25Jt",
        totalBalance = "Rp10Jt",
        progress = 0.5f
    ),
    AccountSavingModel(
        icon = R.drawable.dummy_category_dana_darurat,
        accountIcon = R.drawable.dummy_bank_jago,
        target = "23 November 2024",
        savingName = "Dana Mendesak",
        targetBalance = "Rp5Jt",
        totalBalance = "Rp10Jt",
        progress = 0.6f
    )
)