/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.onboard

import androidx.lifecycle.viewModelScope
import com.bluhabit.blu.android.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class OnboardViewModel @Inject constructor():BaseViewModel<OnboardState,OnboardAction,OnboardEffect>(
    OnboardState()
) {
    override fun onAction(action: OnboardAction) {
        viewModelScope.launch {
            _effect.send(OnboardEffect.ShowDialog(
                message = "Show Message Test"
            ))
        }
    }
}