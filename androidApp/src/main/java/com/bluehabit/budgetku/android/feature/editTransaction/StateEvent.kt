/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.editTransaction

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

enum class EditTransactionBottomSheetType {
    VALUE, NOTE, CATEGORY, ACCOUNT, DATE
}

@Immutable
@Parcelize
data class EditTransactionState(
    val value: String = "",
    val category: Pair<Int, String> = Pair(0, ""),
    val note: String = "",
    val account: Pair<Int, String> = Pair(0, ""),
    val date: Pair<Int, String> = Pair(0, ""),
    val bottomSheetType: EditTransactionBottomSheetType = EditTransactionBottomSheetType.VALUE
) : Parcelable

sealed interface EditTransactionEvent {
    object EditValue : EditTransactionEvent
    object EditCategory: EditTransactionEvent
    object EditNote: EditTransactionEvent
    object EditAccount: EditTransactionEvent
    object EditDate: EditTransactionEvent
    object SaveChanges: EditTransactionEvent
}