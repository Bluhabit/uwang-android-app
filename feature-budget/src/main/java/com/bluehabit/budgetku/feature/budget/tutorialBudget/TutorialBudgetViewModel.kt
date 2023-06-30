/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.tutorialBudget

import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.budgetku.feature.budget.tutorialBudget.TutorialBudgetAction
import com.bluehabit.budgetku.feature.budget.tutorialBudget.TutorialBudgetIntent
import com.bluehabit.budgetku.feature.budget.tutorialBudget.TutorialBudgetState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TutorialBudgetViewModel @Inject constructor(

) : MviViewModel<TutorialBudgetState, TutorialBudgetIntent, TutorialBudgetAction>(TutorialBudgetState()) {

    override fun onAction(action: TutorialBudgetAction) {

    }


}