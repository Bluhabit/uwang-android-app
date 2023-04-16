/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createAccount

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

enum class ScreenType {
    INPUT_AMOUNT,
    MAIN,
    SELECT_ACCOUNT
}

@Immutable
@Parcelize
data class CreateAccountState(
    val screenType: ScreenType = ScreenType.MAIN
) : Parcelable

@Immutable
@Parcelize
data class CreateAccountDataState(
    val a: String = ""
) : Parcelable

sealed interface CreateAccountEvent {
    object Default : CreateAccountEvent
}