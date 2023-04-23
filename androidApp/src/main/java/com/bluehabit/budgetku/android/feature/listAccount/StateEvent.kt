/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.listAccount

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class ListAccountState(
    val tab: Int = 0
) : Parcelable

@Immutable
@Parcelize
data class ListAccountDataState(
    val a: String = ""
) : Parcelable

sealed interface ListAccountEvent {

}