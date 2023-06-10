/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.createTransaction

import android.os.Parcelable
import com.bluehabit.budgetku.data.model.account.AccountModel
import com.bluehabit.budgetku.data.remote.dummy.dummyAccountsHome
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.time.LocalDate
import javax.annotation.concurrent.Immutable

enum class CreateTransactionBottomSheetType {
    CATEGORY,
    DATE_PICKER,
    CANCEL_CONFIRMATION
}

@Immutable
@Parcelize
data class CreateTransactionState(
    val step: Int = 1,
    val bottomSheetType: CreateTransactionBottomSheetType = CreateTransactionBottomSheetType.CATEGORY,
    val nominal:String="",
    val tempNominal:String="",
    val isExpenses:Boolean=true,
    val selectedAccount:String="",
    val transactionName:String="",
    val selectedCategory:String="",
    val transactionDate:LocalDate?=null,

    val transactionCategory:String="",
    //feedback
    val feedback:String=""
) : Parcelable

@Immutable
@Parcelize
data class CreateTransactionDataState(
    val accounts:@RawValue List<AccountModel> = dummyAccountsHome
) : Parcelable

sealed interface CreateTransactionEvent {
    object AddMoreTransaction : CreateTransactionEvent
    object NexPage : CreateTransactionEvent
    object PrevPage : CreateTransactionEvent

    data class ChangeBottomSheet(val bottomSheetType: CreateTransactionBottomSheetType) : CreateTransactionEvent

    object ClearNominal: CreateTransactionEvent
    object RemoveNominal: CreateTransactionEvent
    data class Input(val nominal:String): CreateTransactionEvent
}