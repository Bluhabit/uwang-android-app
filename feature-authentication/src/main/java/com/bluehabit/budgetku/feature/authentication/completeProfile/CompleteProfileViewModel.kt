/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.completeProfile

import app.trian.mvi.ui.viewModel.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompleteProfileViewModel @Inject constructor(
) : MviViewModel<CompleteProfileState, CompleteProfileIntent,CompleteProfileAction>(CompleteProfileState()) {

    override fun onAction(action: CompleteProfileAction) {

    }

}