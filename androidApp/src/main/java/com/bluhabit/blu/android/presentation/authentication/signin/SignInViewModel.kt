/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signin

import com.bluhabit.blu.android.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel
@Inject constructor(): BaseViewModel<SignInState, SignInAction, SignInEffect>(
    SignInState(
        emailState = ""
    )
) {
    override fun onAction(action: SignInAction) {
       when (action) {
           is SignInAction.EmailAction -> updateState { copy(emailState = action.value) }
           is SignInAction.PasswordAction -> updateState {
               copy(
                   passwordState = action.value,
                   passwordVisibility = action.visibility
               )
           }

           is SignInAction.ButtonAction -> updateState { copy(buttonEnabled = action.enabled) }
           is SignInAction.OtpNumberAction -> updateState { copy(
               otpNumberState = action.value,
           ) }
       }
    }
}