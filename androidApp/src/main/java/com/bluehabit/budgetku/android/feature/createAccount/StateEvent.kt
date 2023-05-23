/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createAccount

import android.os.Parcelable
import com.bluehabit.budgetku.data.model.CategoryFinancialAccountModel
import com.bluehabit.budgetku.data.remote.dummy.dummyFinancialAccount
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

enum class ScreenType {
    INPUT_AMOUNT,
    MAIN,
    SELECT_ACCOUNT
}

@Immutable
@Parcelize
data class CreateAccountState(
    val screenType: ScreenType = ScreenType.MAIN,
    val nominal:String="0",
    val tempNominal:String=""
) : Parcelable

@Immutable
@Parcelize
data class CreateAccountDataState(
    val financialAccount: @RawValue List<CategoryFinancialAccountModel>  = dummyFinancialAccount
) : Parcelable

sealed interface CreateAccountEvent {
    object Default : CreateAccountEvent
    object ClearNominal : CreateAccountEvent
    object RemoveNominal : CreateAccountEvent
    data class InputNominal(val nominal: String) : CreateAccountEvent
}