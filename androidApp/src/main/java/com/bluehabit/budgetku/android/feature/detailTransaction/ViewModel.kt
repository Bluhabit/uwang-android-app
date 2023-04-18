/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.detailTransaction

import com.bluehabit.budgetku.android.base.BaseViewModelData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailTransactionViewModel @Inject constructor(
) : BaseViewModelData<DetailTransactionState, DetailTransactionDataState, DetailTransactionEvent>(
    DetailTransactionState(),
    DetailTransactionDataState()
) {
    init {
        handleActions()
    }

    override fun handleActions() = onEvent {}

}