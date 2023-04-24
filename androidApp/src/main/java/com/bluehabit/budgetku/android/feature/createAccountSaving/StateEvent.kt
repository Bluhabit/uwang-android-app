/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createAccountSaving

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

enum class ScreenTypeCreateAccountSaving(
    val step: Int = 0
) {
    INPUT_CATEGORY(0),
    INPUT_PURPOSE(1),
    INPUT_ACCOUNT(2),
    INPUT_DETAIL(3),
    INPUT_AMOUNT(4),
    INPUT_RESULT(5),
    INPUT_FEEDBACK(6),
}

@Immutable
@Parcelize
data class CreateAccountSavingState(
    val screenType: ScreenTypeCreateAccountSaving =
        ScreenTypeCreateAccountSaving.INPUT_DETAIL
) : Parcelable

@Immutable
@Parcelize
data class CreateAccountSavingDataState(
    val a: String = ""
) : Parcelable

sealed interface CreateAccountSavingEvent {
}