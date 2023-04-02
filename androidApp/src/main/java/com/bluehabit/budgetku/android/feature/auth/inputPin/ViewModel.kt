/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.inputPin

import com.bluehabit.budgetku.android.base.BaseViewModel
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
