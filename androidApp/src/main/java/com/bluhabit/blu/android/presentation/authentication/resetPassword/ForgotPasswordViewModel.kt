/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.resetPassword

import com.bluhabit.blu.android.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel
@Inject constructor(): BaseViewModel<ForgotPasswordState, ForgotPasswordAction, ForgotPasswordEffect>(
    ForgotPasswordState()
) {
    override fun onAction(action: ForgotPasswordAction) {
        when (action) {
            is ForgotPasswordAction.EmailAction -> {
                updateState {
                    copy(
                        emailState = action.value,
                        emailError = action.error,
                    )
                }
            }
            is ForgotPasswordAction.NextButtonAction -> updateState { copy(nextButtonEnabled = action.enabled) }
        }
    }
}