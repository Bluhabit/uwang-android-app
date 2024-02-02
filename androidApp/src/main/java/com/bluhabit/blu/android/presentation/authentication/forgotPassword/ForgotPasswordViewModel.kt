/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.forgotPassword

import android.os.CountDownTimer
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.bluhabit.blu.android.common.BaseViewModel
import com.bluhabit.blu.android.data.authentication.domain.ForgotPasswordUseCase
import com.bluhabit.blu.android.data.authentication.domain.SetForgotPasswordUseCase
import com.bluhabit.blu.android.data.authentication.domain.VerifyOtpForgotPasswordUseCase
import com.bluhabit.blu.data.common.Response
import com.bluhabit.blu.data.common.executeAsFlow
import com.bluhabit.core.ui.components.textfield.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val verifyOtpForgotPasswordUseCase: VerifyOtpForgotPasswordUseCase,
    private val setForgotPasswordUseCase: SetForgotPasswordUseCase
) : BaseViewModel<ForgotPasswordState, ForgotPasswordAction, ForgotPasswordEffect>(
    ForgotPasswordState()
) {
    private var countDownTimer: CountDownTimer? = null
    override fun onAction(action: ForgotPasswordAction) {
        when (action) {
            is ForgotPasswordAction.OnEmailChange -> onChangeEmail(action.value)
            is ForgotPasswordAction.NextButtonAction -> updateState { copy(nextButtonEnabled = action.enabled) }
            is ForgotPasswordAction.OnConfirmPasswordChange -> onConfirmPasswordChange(action.value)
            is ForgotPasswordAction.OnNewPasswordChange -> onPasswordChange(action.value)
            is ForgotPasswordAction.OnOtpChange -> onOtpChange(action.value)
            is ForgotPasswordAction.OnScreenChange -> updateState { copy(currentScreen = action.screen) }
            ForgotPasswordAction.RequestResetPassword -> onRequestResetPassword()
            ForgotPasswordAction.VerifyOtp -> onVerifyOtpForgotPassword()
            ForgotPasswordAction.SetNewPassword -> onSetForgotPassword()
            is ForgotPasswordAction.OnConfirmPasswordVisibilityChange -> updateState { copy(passwordVisibility = action.visibility) }
            is ForgotPasswordAction.OnNewPasswordVisibilityChange -> updateState {
                copy(confirmPasswordVisibility = action.visibility)
            }
            ForgotPasswordAction.OnCountDownStart -> onCountDownStart()
            ForgotPasswordAction.OnResendOtp -> {}
            is ForgotPasswordAction.OnSentOtpAlertVisibilityChange -> {}
            ForgotPasswordAction.OnVerifyOtp -> onVerifyOtpForgotPassword()
        }
    }


    private fun onCountDownStart() {
        val countDownTime = _state.value.otpSentCountDown
        if (countDownTime > 0) {
            countDownTimer = object : CountDownTimer(countDownTime, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    updateState { copy(otpSentCountDown = millisUntilFinished) }
                }

                override fun onFinish() {
                    countDownTimer?.cancel()
                }
            }
            countDownTimer?.start()
        }
    }

    override fun onCleared() {
        super.onCleared()
        countDownTimer?.cancel()
        _state.value = ForgotPasswordState()
    }


    private fun onOtpChange(otp: String) = viewModelScope.launch {
        updateState { copy(otpNumberState = otp) }
    }

    private fun onChangeEmail(email: String) = viewModelScope.launch {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        updateState {
            copy(
                emailState = email,
                emailInputState = if(isEmailValid) TextFieldState.None else TextFieldState.Error("Email tidak valid.")
            )
        }
    }

    private fun onPasswordChange(password: String) = viewModelScope.launch {
        updateState {
            copy(
                passwordState = password,
                passwordInputState = if(password.isEmpty()) TextFieldState.None else TextFieldState.Error("Password tidak boleh kosong")
            )
        }
    }

    private fun onConfirmPasswordChange(password: String) = viewModelScope.launch {
        updateState {
            copy(
                confirmPasswordState = password,
                confirmPasswordInputState = if(password.isEmpty()) TextFieldState.None else TextFieldState.Error("Password tidak boleh kosong")
            )
        }
    }

    private fun onRequestResetPassword() = viewModelScope.launch {
        executeAsFlow { forgotPasswordUseCase(email = state.value.emailState) }
            .onStart { }
            .onEach {
                when (it) {
                    is Response.Error -> Unit
                    is Response.Result -> {
                        updateState { copy(currentScreen = 1) }
                    }
                }
            }
            .collect()
    }

    private fun onVerifyOtpForgotPassword() = viewModelScope.launch {
        executeAsFlow { verifyOtpForgotPasswordUseCase(otp = state.value.otpNumberState) }
            .onStart { }
            .onEach {
                when (it) {
                    is Response.Error -> Unit
                    is Response.Result -> {
                        updateState { copy(currentScreen = 2) }
                    }
                }
            }
            .collect()
    }

    private fun onSetForgotPassword() = viewModelScope.launch {
        executeAsFlow { setForgotPasswordUseCase(password = state.value.passwordState) }
            .onStart { }
            .onEach {
                when (it) {
                    is Response.Error -> Unit
                    is Response.Result -> {
                        updateState { copy(currentScreen = 3) }
                    }
                }
            }
            .collect()
    }
}