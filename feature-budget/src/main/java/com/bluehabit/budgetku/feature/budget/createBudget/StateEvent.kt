/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.createBudget

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class CreateBudgetState(
    val nominal: String = "",
    val tempNominal: String = "",
    val step:Int=1
) : Parcelable

@Immutable
@Parcelize
data class CreateBudgetDataState(
    val a: String = ""
) : Parcelable

sealed interface CreateBudgetEvent {
    data class Input(val nominal:String): CreateBudgetEvent
    object Clear: CreateBudgetEvent
    object Remove: CreateBudgetEvent

    object Next: CreateBudgetEvent
    object Prev: CreateBudgetEvent
}