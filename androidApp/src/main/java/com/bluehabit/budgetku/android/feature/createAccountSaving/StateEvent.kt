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


enum class CreateAccountSavingBottomSheetType{
    EMERGENCY_FUND,
    CANCEL_CONFIRMATION
}

@Immutable
@Parcelize
data class CreateAccountSavingState(
    val screenType: Int=1,
    val selectedCategory:Int?=null,
    val savingPurpose:String="",
    val selectedAccount:Int?=null,
    val target:String="0",

    val feedback:String="",

    val nominal:String="1,000,000",
    val tempNominal:String="1000000",

    val bottomSheetType: CreateAccountSavingBottomSheetType = CreateAccountSavingBottomSheetType.EMERGENCY_FUND

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

    data class Input(val nominal:String): CreateAccountSavingEvent
    object Clear: CreateAccountSavingEvent
    object Remove: CreateAccountSavingEvent
}