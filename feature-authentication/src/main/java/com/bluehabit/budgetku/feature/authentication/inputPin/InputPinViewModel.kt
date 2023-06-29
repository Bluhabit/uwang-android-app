/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.inputPin

import app.trian.mvi.ui.viewModel.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InputPinViewModel @Inject constructor(
) : MviViewModel<InputPinState,InputPinIntent, InputPinAction>(InputPinState()) {



    override fun onAction(action: InputPinAction) {
        TODO("Not yet implemented")
    }

}
