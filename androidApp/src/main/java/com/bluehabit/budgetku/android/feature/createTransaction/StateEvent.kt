/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createTransaction

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

enum class CreateTransactionBottomSheetType {
    CATEGORY,
    DATE_PICKER
}

@Immutable
@Parcelize
data class CreateTransactionState(
    val step: Int = 1,
    val bottomSheetType: CreateTransactionBottomSheetType = CreateTransactionBottomSheetType.CATEGORY
) : Parcelable

@Immutable
@Parcelize
data class CreateTransactionDataState(
    val a: String = ""
) : Parcelable

sealed interface CreateTransactionEvent {
    object NexPage : CreateTransactionEvent
    object PrevPage : CreateTransactionEvent

    data class ChangeBottomSheet(val bottomSheetType: CreateTransactionBottomSheetType) : CreateTransactionEvent
}