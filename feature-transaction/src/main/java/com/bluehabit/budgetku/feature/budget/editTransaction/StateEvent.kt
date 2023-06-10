/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.editTransaction

import android.os.Parcelable
import com.bluehabit.budgetku.data.model.account.AccountModel
import com.bluehabit.budgetku.data.remote.dummy.dummyAccountsHome
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.time.LocalDate
import javax.annotation.concurrent.Immutable

enum class BottomSheetTypeEditTransaction {
    DATE,
    CATEGORY,
    ACCOUNT,
    CANCEL_CONFORMATION
}

enum class ScreenType {
    NUMPAD,
    MAIN
}

@Immutable
@Parcelize
data class EditTransactionState(
    val bottomSheetType: BottomSheetTypeEditTransaction = BottomSheetTypeEditTransaction.ACCOUNT,
    val screenType: ScreenType = ScreenType.MAIN,

    val isExpenses: Boolean = true,
    val categoryName: String = "Makanan & Minuman",
    val categoryIcon: Int = com.bluehabit.budgetku.data.R.drawable.dummy_category_makan_minum,

    val transactionDate: LocalDate? = LocalDate.now(),

    val accountName: String = "Bank jago",
    val accountIcon: Int = com.bluehabit.budgetku.data.R.drawable.dummy_bank_jago,
    val accountId:String="",

    val nominal: String = "1,000,000",
    val tempNominal: String = "1000000"
) : Parcelable

@Immutable
@Parcelize
data class EditTransactionDataState(
    val accounts: @RawValue List<AccountModel> = dummyAccountsHome
) : Parcelable

sealed interface EditTransactionEvent {
    object ClearNominal : EditTransactionEvent
    object RemoveNominal : EditTransactionEvent

    data class InputNominal(val nominal: String) : EditTransactionEvent
}