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
        icon = R.drawable.ic_dummy_saving,
        accountIcon = R.drawable.dummy_bank_jago,
        target = LocalDateTime.now(),
        savingName = "Motor Baru",
        targetBalance = "Rp30Jt",
        totalBalance = "Rp4Jt",
        progress = 0.5f
    ),
    AccountSavingModel(
        icon = R.drawable.ic_dummy_saving,
        accountIcon = R.drawable.dummy_bank_jago,
        target = LocalDateTime.now(),
        savingName = "Motor Baru",
        targetBalance = "Rp15Jt",
        totalBalance = "Rp4Jt",
        progress = 0.4f
    )
)