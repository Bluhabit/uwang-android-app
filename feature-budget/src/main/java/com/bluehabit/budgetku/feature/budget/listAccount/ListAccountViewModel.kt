/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.listAccount

import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.budgetku.feature.budget.listAccount.ListAccountAction
import com.bluehabit.budgetku.feature.budget.listAccount.ListAccountIntent
import com.bluehabit.budgetku.feature.budget.listAccount.ListAccountState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListAccountViewModel @Inject constructor(
) : MviViewModel<ListAccountState, ListAccountIntent, ListAccountAction>(ListAccountState()) {
    override fun onAction(action: ListAccountAction) {


    }

}