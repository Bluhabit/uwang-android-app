/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.inputPin

import com.bluehabit.core.ui.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InputPinViewModel @Inject constructor(
) : BaseViewModel<InputPinState, InputPinEvent>(InputPinState()) {
    init {
        handleActions()
    }

    override fun handleActions() = onEvent {}

}
