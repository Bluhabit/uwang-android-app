/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signup

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.bluhabit.blu.android.common.BaseViewModel
import com.bluhabit.blu.android.data.authentication.domain.SignUpBasicUseCase
import com.bluhabit.blu.android.data.authentication.domain.VerifyOtpSignUpBasicUseCase
import com.bluhabit.blu.data.common.Response
import com.bluhabit.blu.data.common.executeAsFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpBasicUseCase: SignUpBasicUseCase,
    private val verifyOtpSignUpBasicUseCase: VerifyOtpSignUpBasicUseCase
) : BaseViewModel<SignUpState, SignUpAction, SignUpEffect>(SignUpState()) {
    override fun onAction(action: SignUpAction) {
        when (action) {
            is SignUpAction.OnEmailChange -> onEmailChange(action.value)
            is SignUpAction.OnPasswordChange -> onPasswordChange(action.value)
            is SignUpAction.OnPasswordConfirmationChange -> onPasswordConfirmationChange(action.value)
            is SignUpAction.OnOtpChange -> onOtpChange(action.otp)
            is SignUpAction.OnPasswordConfirmationVisibilityChange -> updateState { copy(passwordConfirmationVisibility = action.visibility) }
            is SignUpAction.OnPasswordVisibilityChange -> updateState { copy(passwordVisibility = action.visibility) }
            is SignUpAction.OnScreenChange -> updateState { copy(currentScreen = action.screen) }
            SignUpAction.SignUpBasic -> signUpBasic()
            SignUpAction.VerifyOtpUpBasic -> verifyOtp()
        }
    }

    private fun onEmailChange(email: String) = viewModelScope.launch {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        updateState { copy(emailState = email, emailError = !isEmailValid) }
    }

    private fun onPasswordChange(password: String) = viewModelScope.launch {
        updateState { copy(passwordState = email, passwordError = password.isEmpty()) }
    }

    private fun onPasswordConfirmationChange(password: String) = viewModelScope.launch {
        updateState { copy(passwordConfirmationState = password, passwordConfirmationError = password.isEmpty()) }
    }

    private fun onOtpChange(password: String) = viewModelScope.launch {
        updateState { copy(passwordConfirmationState = password, passwordConfirmationError = password.isEmpty()) }
    }

    private fun signUpBasic() = viewModelScope.launch {
        executeAsFlow { signUpBasicUseCase(email = state.value.emailState, password = state.value.passwordState) }
            .onStart {}
            .onEach {
            when (it) {
                is Response.Error -> Unit
                is Response.Result -> {
                    updateState { copy(currentScreen = 1) }
                }
            }
        }.collect()
    }

    private fun verifyOtp() = viewModelScope.launch {
        executeAsFlow { verifyOtpSignUpBasicUseCase(otp = state.value.otpState) }
            .onStart {}
            .onEach {
                when (it) {
                    is Response.Error -> Unit
                    is Response.Result -> {
                        _effect.send(SignUpEffect.NavigateToCompleteProfile)
                    }
                }
            }
            .collect()
    }
}