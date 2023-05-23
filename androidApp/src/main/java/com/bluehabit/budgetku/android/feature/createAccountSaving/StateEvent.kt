/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createAccountSaving

import android.os.Parcelable
import com.bluehabit.budgetku.data.model.account.AccountModel
import com.bluehabit.budgetku.data.model.category.SavingCategory
import com.bluehabit.budgetku.data.remote.dummy.dummyAccountsHome
import com.bluehabit.budgetku.data.remote.dummy.dummySavingCategory
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
    val accounts: @RawValue List<AccountModel> = dummyAccountsHome,
    val categories: @RawValue List<SavingCategory> = dummySavingCategory
) : Parcelable

sealed interface CreateAccountSavingEvent {
    object Next:CreateAccountSavingEvent
    object Prev:CreateAccountSavingEvent
}