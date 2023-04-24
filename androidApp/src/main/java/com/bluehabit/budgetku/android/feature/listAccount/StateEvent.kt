/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.listAccount

import android.os.Parcelable
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.data.model.account.AccountModel
import com.bluehabit.budgetku.data.model.account.AccountSavingModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class ListAccountState(
    val selectedTab: Int = 0
) : Parcelable

@Immutable
@Parcelize
data class ListAccountDataState(
    val balance: BigDecimal = BigDecimal(6_000_000),
    val accounts: @RawValue List<AccountModel> = listOf(
        AccountModel(
            icon = R.drawable.ic_jago,
            accountName = "Bank Jago",
            accountBalance = BigDecimal(1_000_000)
        ),
        AccountModel(
            icon = R.drawable.ic_bca,
            accountName = "Bank BCA",
            accountBalance = BigDecimal(10_000_000)
        ),
        AccountModel(
            icon = R.drawable.ic_jago,
            accountName = "Bank Jago",
            accountBalance = BigDecimal(500_000),
            connectedSaving = AccountSavingModel(
                icon =R.drawable.ic_dummy_saving,
                accountIcon =  R.drawable.ic_jago,
                target = LocalDateTime.now(),
                savingName = "Motor Baru",
                targetBalance = "Rp30Jt",
                totalBalance = "Rp4Jt",
                progress = 0.5f
            ),
        ),
    ),
    val savingAccounts: @RawValue List<AccountSavingModel> = listOf(
        AccountSavingModel(
            icon =R.drawable.ic_dummy_saving,
            accountIcon =  R.drawable.ic_jago,
            target = LocalDateTime.now(),
            savingName = "Motor Baru",
            targetBalance = "Rp30Jt",
            totalBalance = "Rp4Jt",
            progress = 0.5f
        ),
        AccountSavingModel(
            icon =R.drawable.ic_dummy_saving,
            accountIcon = R.drawable.ic_bca,
            target = LocalDateTime.now(),
            savingName = "Motor Baru",
            targetBalance = "Rp15Jt",
            totalBalance = "Rp4Jt",
            progress = 0.4f
        )
    ),
) : Parcelable

sealed interface ListAccountEvent {

}