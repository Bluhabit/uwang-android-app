/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.transaction.createTransaction

import com.bluehabit.budgetku.feature.transaction.createTransaction.components.CreateTransactionBottomSheetType

sealed interface CreateTransactionAction {
    object AddMoreTransaction : CreateTransactionAction
    object NexPage : CreateTransactionAction
    object PrevPage : CreateTransactionAction

    data class ChangeBottomSheet(val bottomSheetType: CreateTransactionBottomSheetType) : CreateTransactionAction

    object ClearNominal: CreateTransactionAction
    object RemoveNominal: CreateTransactionAction
    data class Input(val nominal:String): CreateTransactionAction
}