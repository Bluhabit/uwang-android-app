/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.resultCreateBudget

import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.budgetku.feature.budget.resultCreateBudget.ResultCreateBudgetAction
import com.bluehabit.budgetku.feature.budget.resultCreateBudget.ResultCreateBudgetIntent
import com.bluehabit.budgetku.feature.budget.resultCreateBudget.ResultCreateBudgetState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultCreateBudgetViewModel @Inject constructor(
) : MviViewModel<ResultCreateBudgetState, ResultCreateBudgetIntent, ResultCreateBudgetAction>(
    ResultCreateBudgetState()
) {

    override fun onAction(action: ResultCreateBudgetAction) {

    }

}