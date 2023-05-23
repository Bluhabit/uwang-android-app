/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.budget

import android.os.Parcelable
import com.bluehabit.budgetku.data.model.ExpensesBudgetCategory
import com.bluehabit.budgetku.data.remote.dummy.dummyBudgetCategory
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

enum class BottomSheetBudget {
    FAB,
    SORT
}

@Immutable
@Parcelize
data class BudgetState(
    val bottomSheetType: BottomSheetBudget = BottomSheetBudget.FAB
) : Parcelable

@Immutable
@Parcelize
data class BudgetDataState(
    val currentMonth: String = "April 2023",
    val hasBudget: Boolean = true,
    val expensesCategory: @RawValue List<ExpensesBudgetCategory> = dummyBudgetCategory
) : Parcelable

sealed class BudgetEvent {
}