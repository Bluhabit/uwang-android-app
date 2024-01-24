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
import com.bluhabit.blu.android.data.authentication.domain.SignInGoogleUseCase
import com.bluhabit.blu.android.data.authentication.domain.SignUpBasicUseCase
import com.bluhabit.blu.android.data.authentication.domain.VerifyOtpSignUpBasicUseCase
import com.bluhabit.blu.data.common.Response
import com.bluhabit.blu.data.common.executeAsFlow
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpBasicUseCase: SignUpBasicUseCase,
    private val verifyOtpSignUpBasicUseCase: VerifyOtpSignUpBasicUseCase,
    private val signInGoogleUseCase: SignInGoogleUseCase
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
            is SignUpAction.OnButtonEnabledChange -> updateState { copy(buttonEnabled = false) }
            is SignUpAction.OnSignInGoogle -> signUpGoogle(action.authResult)
        }
    }
    private fun onEmailChange(email: String) = viewModelScope.launch {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = state.value.passwordState.isNotEmpty()
        val isConfirmPasswordValid = state.value.passwordConfirmationState.isNotEmpty()
        updateState {
            copy(
                emailState = email,
                emailError = !isEmailValid,
                emailErrorText = if (isEmailValid) "" else "Format email tidak valid",
                buttonEnabled = isPasswordValid && isConfirmPasswordValid && isEmailValid
            )
        }
    }

    private fun onPasswordChange(password: String) = viewModelScope.launch {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(state.value.emailState).matches()
        val isPasswordValid = password.isNotEmpty() && password.length >= 8
        val isConfirmPasswordValid = state.value.passwordConfirmationState.isNotEmpty()
        updateState {
            copy(
                passwordState = password,
                passwordError = !isPasswordValid,
                passwordErrorText = if (isPasswordValid) "" else "Password harus minimal 8 karakter",
                buttonEnabled = isPasswordValid && isConfirmPasswordValid && isEmailValid
            )
        }
    }

    private fun onPasswordConfirmationChange(password: String) = viewModelScope.launch {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(state.value.emailState).matches()
        val isPasswordValid = state.value.passwordState.isNotEmpty()
        val isConfirmPasswordValid = password == state.value.passwordState
        updateState {
            copy(
                passwordConfirmationState = password,
                passwordConfirmationError = !isConfirmPasswordValid,
                passwordConfirmationErrorText = if (isConfirmPasswordValid) "" else "Password tidak sesuai",
                buttonEnabled = isPasswordValid && isConfirmPasswordValid && isEmailValid
            )
        }
    }

    private fun onOtpChange(otp: String) = viewModelScope.launch {
        val isOtpValid = otp.isNotEmpty() && otp.length >= 4
        updateState { copy(otpState = otp, otpError = isOtpValid) }
    }

    private fun signUpBasic() = viewModelScope.launch {
        executeAsFlow { signUpBasicUseCase(email = state.value.emailState, password = state.value.passwordState) }
            .onStart {}
            .onEach {
                when (it) {
                    is Response.Error -> Unit
                    is Response.Result -> {
                        updateState {
                            copy(
                                currentScreen = 1,
                                emailState = "",
                                passwordState = "",
                                passwordConfirmationState = "",
                                buttonEnabled = false
                            )
                        }
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

    private fun signUpGoogle(task: Task<GoogleSignInAccount>) = viewModelScope.launch {
        val auth = task.await()
        executeAsFlow { signInGoogleUseCase(auth.idToken.orEmpty()) }
            .onStart { }
            .onEach {
                when (it) {
                    is Response.Error -> Unit
                    is Response.Result -> {
                        if (it.data.credential.profile.isEmpty()) {
                            _effect.send(SignUpEffect.NavigateToCompleteProfile)
                        } else {
                            _effect.send(SignUpEffect.NavigateToMain)
                        }
                    }
                }
            }
            .collect()
    }
}