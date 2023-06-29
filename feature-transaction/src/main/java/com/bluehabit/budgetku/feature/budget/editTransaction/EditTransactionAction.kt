/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.editTransaction

sealed interface EditTransactionAction {
    object ClearNominal : EditTransactionAction
    object RemoveNominal : EditTransactionAction

    data class InputNominal(val nominal: String) : EditTransactionAction
}