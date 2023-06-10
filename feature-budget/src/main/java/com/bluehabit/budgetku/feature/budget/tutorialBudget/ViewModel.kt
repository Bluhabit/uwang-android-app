/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.tutorialBudget

import com.bluehabit.core.ui.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TutorialBudgetViewModel @Inject constructor(

) : BaseViewModel<TutorialBudgetState, TutorialBudgetEvent>(TutorialBudgetState()) {

    override fun handleActions() {
        TODO("Not yet implemented")
    }



}