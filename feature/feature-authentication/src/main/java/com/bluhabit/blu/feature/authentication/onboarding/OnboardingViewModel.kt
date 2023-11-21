/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.feature.authentication.onboarding

import app.trian.mvi.ui.viewModel.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
) : MviViewModel<OnboardingState, OnboardingAction>(
    OnboardingState()
) {
    override fun onAction(action: OnboardingAction) {
        when (action) {
            OnboardingAction.CheckSession -> async {}
        }
    }
}