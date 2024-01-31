/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signup

import com.bluhabit.blu.android.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel
@Inject constructor(): BaseViewModel<SignUpState, SignUpAction, SignUpEffect>(
    SignUpState(
        emailState = ""
    )
) {
    override fun onAction(action: SignUpAction) {
       when (action) {
           is SignUpAction.EmailAction -> updateState { copy(emailState = action.value) }
           is SignUpAction.PasswordAction -> updateState {
               copy(
                   passwordState = action.value,
                   passwordVisibility = action.visibility
               )
           }

           is SignUpAction.ButtonAction -> updateState { copy(buttonEnabled = action.enabled) }
           is SignUpAction.PasswordConfirmationAction -> updateState {
               copy(
                   passwordState = action.value,
                   passwordVisibility = action.visibility
               )
           }
       }
    }
}