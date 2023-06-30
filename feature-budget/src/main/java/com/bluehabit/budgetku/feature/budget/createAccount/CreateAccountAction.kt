/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.createAccount

sealed interface CreateAccountAction {
    object Default : CreateAccountAction
    object ClearNominal : CreateAccountAction
    object RemoveNominal : CreateAccountAction
    data class InputNominal(val nominal: String) : CreateAccountAction
}