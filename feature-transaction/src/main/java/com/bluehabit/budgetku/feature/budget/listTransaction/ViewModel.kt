/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.listTransaction

import com.bluehabit.core.ui.viewModel.BaseViewModelData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListTransactionViewModel @Inject constructor(
) : BaseViewModelData<ListTransactionState, ListTransactionDataState, ListTransactionEvent>(ListTransactionState(), ListTransactionDataState()) {
    init {
        handleActions()
    }

    override fun handleActions() = onEvent {}

}