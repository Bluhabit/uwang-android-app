/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.completeProfile

import com.bluehabit.core.ui.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompleteProfileViewModel @Inject constructor(
) : BaseViewModel<CompleteProfileState, CompleteProfileEvent>(CompleteProfileState()) {
    init {
        handleActions()
    }

    override fun handleActions() = onEvent {}

}