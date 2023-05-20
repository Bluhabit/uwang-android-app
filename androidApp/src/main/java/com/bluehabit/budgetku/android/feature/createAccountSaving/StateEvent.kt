/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createAccountSaving

import android.os.Parcelable
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.data.model.account.AccountModel
import com.bluehabit.budgetku.data.model.category.SavingCategory
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.math.BigDecimal
import javax.annotation.concurrent.Immutable



@Immutable
@Parcelize
data class CreateAccountSavingState(
    val screenType: Int=0,
    val selectedCategory:Int=0,
    val savingPurpose:String="",
    val selectedAccount:Int=0,
    val amount:BigDecimal= BigDecimal.ZERO,
    val target:String="0"
) : Parcelable

@Immutable
@Parcelize
data class CreateAccountSavingDataState(
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
            accountBalance = BigDecimal(500_000)
        ),
    ),
    val categories: @RawValue List<SavingCategory> = listOf(
        SavingCategory(
            categoryName = "Belanja",
            icon = com.bluehabit.budgetku.data.R.drawable.ic_dummy_saving
        ),
        SavingCategory(
            categoryName = "Liburan",
            icon = com.bluehabit.budgetku.data.R.drawable.ic_dummy_saving
        ),
        SavingCategory(
            categoryName = "Rumah",
            icon = com.bluehabit.budgetku.data.R.drawable.ic_dummy_saving
        ),
        SavingCategory(
            categoryName = "Menikah",
            icon = com.bluehabit.budgetku.data.R.drawable.ic_dummy_saving
        ),
        SavingCategory(
            categoryName = "Pendidikan",
            icon =com.bluehabit.budgetku.data.R.drawable.ic_dummy_saving
        ),
        SavingCategory(
            categoryName = "Dana Darurat",
            icon = com.bluehabit.budgetku.data.R.drawable.ic_dummy_saving
        ),
        SavingCategory(
            categoryName = "Kendaraan",
            icon = com.bluehabit.budgetku.data.R.drawable.ic_dummy_saving
        ),
        SavingCategory(
            categoryName = "Pinjaman",
            icon = com.bluehabit.budgetku.data.R.drawable.ic_dummy_saving
        ),
        SavingCategory(
            categoryName = "Kredit",
            icon = com.bluehabit.budgetku.data.R.drawable.ic_dummy_saving
        ),
        SavingCategory(
            categoryName = "Investasi",
            icon = com.bluehabit.budgetku.data.R.drawable.ic_dummy_saving
        )
    )
) : Parcelable

sealed interface CreateAccountSavingEvent {
    object Next:CreateAccountSavingEvent
    object Prev:CreateAccountSavingEvent
}