/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.createAccountSaving

sealed interface CreateAccountSavingAction {
    object Next: CreateAccountSavingAction
    object Prev: CreateAccountSavingAction

    data class Input(val nominal:String): CreateAccountSavingAction
    object Clear: CreateAccountSavingAction
    object Remove: CreateAccountSavingAction
}