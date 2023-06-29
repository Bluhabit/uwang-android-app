/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.createPin

import app.trian.mvi.ui.viewModel.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreatePinViewModel @Inject constructor(
) : MviViewModel<CreatePinState,CreatePinIntent, CreatePinAction>(CreatePinState()) {

    override fun onAction(action: CreatePinAction) {

    }


}
