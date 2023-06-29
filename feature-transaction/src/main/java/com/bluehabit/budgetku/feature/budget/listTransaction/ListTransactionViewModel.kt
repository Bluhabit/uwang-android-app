/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.listTransaction

import app.trian.mvi.ui.viewModel.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListTransactionViewModel @Inject constructor(
) : MviViewModel<ListTransactionState,ListTransactionIntent, ListTransactionAction>(ListTransactionState()) {

    override fun onAction(action: ListTransactionAction) {
        TODO("Not yet implemented")
    }

}