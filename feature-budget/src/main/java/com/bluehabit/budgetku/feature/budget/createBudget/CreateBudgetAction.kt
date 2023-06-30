/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.createBudget

sealed interface CreateBudgetAction {
    data class Input(val nominal:String): CreateBudgetAction
    object Clear: CreateBudgetAction
    object Remove: CreateBudgetAction

    object Next: CreateBudgetAction
    object Prev: CreateBudgetAction
}