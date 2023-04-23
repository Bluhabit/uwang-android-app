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

enum class BottomSheetTypeEditTransaction {
    DATE,
    CATEGORY,
    ACCOUNT
}

enum class ScreenType {
    NUMPAD,
    MAIN
}

@Immutable
@Parcelize
data class EditTransactionState(
    val bottomSheetType: BottomSheetTypeEditTransaction = BottomSheetTypeEditTransaction.ACCOUNT,
    val screenType: ScreenType = ScreenType.MAIN
) : Parcelable

@Immutable
@Parcelize
data class EditTransactionDataState(
    val a: String = ""
) : Parcelable

sealed interface EditTransactionEvent {
}