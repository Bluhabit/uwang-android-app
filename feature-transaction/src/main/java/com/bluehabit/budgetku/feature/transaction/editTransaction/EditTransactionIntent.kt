/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.transaction.editTransaction

sealed interface EditTransactionIntent {
    object ClearNominal : EditTransactionIntent
    object RemoveNominal : EditTransactionIntent

    data class InputNominal(val nominal: String) : EditTransactionIntent
}