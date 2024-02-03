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
import com.bluhabit.blu.android.data.authentication.domain.ResendOtpForgotPasswordUseCase
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
    private val resendOtpForgotPasswordUseCase: ResendOtpForgotPasswordUseCase,
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
            ForgotPasswordAction.SetNewPassword -> onSetNewPasswordForgotPassword()
            is ForgotPasswordAction.OnConfirmPasswordVisibilityChange -> updateState { copy(confirmPasswordVisibility  = action.visibility) }
            is ForgotPasswordAction.OnNewPasswordVisibilityChange -> updateState { copy(passwordVisibility = action.visibility) }
            ForgotPasswordAction.OnCountDownStart -> onCountDownStart()
            ForgotPasswordAction.OnResendOtp -> onResendOtpForgotPassword()
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
                    updateState { copy(otpSentCountDown = 0) }
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
        val isEmailValid = when{
            email.length > 1 && !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> TextFieldState.Error("Email tidak valid")
            else -> TextFieldState.None
        }
        updateState {
            copy(
                emailState = email,
                emailInputState = isEmailValid
            )
        }
    }

    private fun onPasswordChange(password: String) = viewModelScope.launch {
        val passwordState = when {
            password.length == 1 -> TextFieldState.WithHint("Password minimal terdiri dari 8 karakter")
            password.length in 2..7 -> TextFieldState.Error("Password minimal 8 karakter.")
            else -> TextFieldState.Success("Sekarang sudah aman")
        }
        updateState {
            copy(
                passwordState = password,
                passwordInputState = passwordState
            )
        }
    }

    private fun onConfirmPasswordChange(password: String) = viewModelScope.launch {
        val passwordState = when {
            password.length == 1 -> TextFieldState.WithHint("Password harus sama")
            password.length in 2..7 -> TextFieldState.Error("Password tidak sama.")
            password != _state.value.passwordState ->  TextFieldState.Error("Password tidak sama.")
            else -> TextFieldState.Success("Password sesuai")
        }
        updateState {
            copy(
                confirmPasswordState = password,
                confirmPasswordInputState = passwordState
            )
        }
    }

    //send
    private fun onRequestResetPassword() = viewModelScope.launch {
        executeAsFlow { forgotPasswordUseCase(email = state.value.emailState) }
            .onStart {
                updateState { copy(showLoading = true) }
            }
            .onEach {
                updateState { copy(showLoading = false) }
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
            .onStart {
                updateState { copy(showLoading = true) }
            }
            .onEach {
                updateState { copy(showLoading = false) }
                when (it) {
                    is Response.Error -> Unit
                    is Response.Result -> {
                        updateState { copy(currentScreen = 2) }
                    }
                }
            }
            .collect()
    }

    private fun onResendOtpForgotPassword() = viewModelScope.launch {
        executeAsFlow { resendOtpForgotPasswordUseCase() }
            .onStart {
                updateState { copy(showLoading = true) }
            }
            .onEach {
                updateState { copy(showLoading = false) }
                when (it) {
                    is Response.Error -> Unit
                    is Response.Result -> {}
                }
            }
            .collect()
    }

    private fun onSetNewPasswordForgotPassword() = viewModelScope.launch {
        executeAsFlow { setForgotPasswordUseCase(password = state.value.passwordState) }
            .onStart {
                updateState { copy(showLoading = true) }
            }
            .onEach {
                updateState { copy(showLoading = false) }
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