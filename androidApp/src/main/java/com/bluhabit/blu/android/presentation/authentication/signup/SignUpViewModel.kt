/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signup

import android.os.CountDownTimer
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.bluhabit.blu.android.common.BaseViewModel
import com.bluhabit.blu.android.data.authentication.domain.CompleteProfileSignUpUseCase
import com.bluhabit.blu.android.data.authentication.domain.ResendOtpSignUpUseCase
import com.bluhabit.blu.android.data.authentication.domain.SignUpBasicUseCase
import com.bluhabit.blu.android.data.authentication.domain.VerifyOtpSignUpBasicUseCase
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
class SignUpViewModel @Inject constructor(
    private val signUpBasicUseCase: SignUpBasicUseCase,
    private val verifyOtpSignUpBasicUseCase: VerifyOtpSignUpBasicUseCase,
    private val resendOtpSignUpUseCase: ResendOtpSignUpUseCase,
    private val completeProfileSignUpUseCase: CompleteProfileSignUpUseCase
) : BaseViewModel<SignUpState, SignUpAction, SignUpEffect>(SignUpState()) {
    private var countDownTimer: CountDownTimer? = null

    override fun onAction(action: SignUpAction) {
        when (action) {
            is SignUpAction.OnEmailChange -> onEmailChange(action.value)
            is SignUpAction.OnPasswordChange -> onPasswordChange(action.value)
            is SignUpAction.OnPasswordConfirmationChange -> onPasswordConfirmationChange(action.value)
            is SignUpAction.OnOtpChange -> {
                updateState { copy(otpNumberState = action.value) }
            }

            is SignUpAction.OnPasswordConfirmationVisibilityChange -> updateState { copy(passwordConfirmationVisibility = action.visibility) }
            is SignUpAction.OnPasswordVisibilityChange -> updateState { copy(passwordVisibility = action.visibility) }
            is SignUpAction.OnScreenChange -> updateState { copy(currentScreen = action.screen) }
            SignUpAction.SignUpBasic -> signUpBasic()
            SignUpAction.OnVerifyOtp -> verifyOtp()
            is SignUpAction.OnButtonEnabledChange -> updateState { copy(signUpButtonEnabled = false) }
            is SignUpAction.OnSentOtpAlertVisibilityChange -> updateState { copy(otpSentAlertVisibility = action.visibility) }
            SignUpAction.OnCountDownStart -> onCountDownStart()
            SignUpAction.OnResendOtp -> resendOtp()
            is SignUpAction.OnShowBottomSheet -> updateState { copy(bottomSheetType = action.type) }
            is SignUpAction.OnDateOfBirthChange -> updateState { copy(dateOfBirthState = action.value) }
            is SignUpAction.OnFullNameChange -> updateState { copy(fullNameState = action.value) }
            is SignUpAction.OnGenderChange -> updateState { copy(genderState = action.value) }
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
        updateState {
            SignUpState() // Clearing saved state
        }
    }

    private fun onEmailChange(email: String) = viewModelScope.launch {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = state.value.passwordState.isNotEmpty()
        val isConfirmPasswordValid = state.value.confirmPasswordState.isNotEmpty()

        val emailInputState = if (isEmailValid) TextFieldState.None else TextFieldState.Error("Format email tidak valid")
        updateState {
            copy(
                emailState = email,
                emailInputState = emailInputState,
                signUpButtonEnabled = isPasswordValid && isConfirmPasswordValid && isEmailValid
            )
        }
    }

    private fun onPasswordChange(password: String) = viewModelScope.launch {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(state.value.emailState).matches()
        val isPasswordValid = password.isNotEmpty() && password.length >= 8
        val isConfirmPasswordValid = state.value.confirmPasswordState.isNotEmpty()
        val passwordInputState = if (isPasswordValid) TextFieldState.None else TextFieldState.Error("Password harus minimal 8 karakter")
        updateState {
            copy(
                passwordState = password,
                confirmPasswordInputState = passwordInputState,
                signUpButtonEnabled = isPasswordValid && isConfirmPasswordValid && isEmailValid
            )
        }
    }

    private fun onPasswordConfirmationChange(password: String) = viewModelScope.launch {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(state.value.emailState).matches()
        val isPasswordValid = state.value.passwordState.isNotEmpty()
        val isConfirmPasswordValid = password == state.value.passwordState
        val confirmPasswordInputState = if (isConfirmPasswordValid) TextFieldState.None else TextFieldState.Error("Password tidak sesuai.")
        updateState {
            copy(
                confirmPasswordState = password,
                confirmPasswordInputState = confirmPasswordInputState,
                signUpButtonEnabled = isPasswordValid && isConfirmPasswordValid && isEmailValid
            )
        }
    }

    private fun signUpBasic() = viewModelScope.launch {
        executeAsFlow { signUpBasicUseCase(email = state.value.emailState, password = state.value.passwordState) }
            .onStart {
                updateState { copy(signUpButtonEnabled = false, showLoading = true) }
            }
            .onEach {
                updateState { copy(showLoading = false) }
                when (it) {
                    is Response.Error -> {
                        updateState { copy(signUpButtonEnabled = true) }
                    }

                    is Response.Result -> {
                        updateState { copy(currentScreen = 1) }
                    }
                }
            }.collect()
    }

    private fun verifyOtp() = viewModelScope.launch {
        executeAsFlow { verifyOtpSignUpBasicUseCase(otp = state.value.otpNumberState) }
            .onStart { updateState { copy(showLoading = true) } }
            .onEach {
                updateState { copy(showLoading = false) }
                when (it) {
                    is Response.Error -> Unit
                    is Response.Result -> {
                        updateState { copy(currentScreen = 2) }
                        _effect.send(SignUpEffect.NavigateToCompleteProfile)
                    }
                }
            }
            .collect()
    }

    private fun completeProfile() = viewModelScope.launch {
        val state =_state.value
        executeAsFlow { completeProfileSignUpUseCase(
            fullName = state.fullNameState,
            gender = state.genderState,
            dateOfBirth = state.dateOfBirthState
        ) }
            .onStart { updateState { copy(showLoading = true) } }
            .onEach {
                updateState { copy(showLoading = false) }
                when (it) {
                    is Response.Error -> Unit
                    is Response.Result -> {
                        updateState { copy(currentScreen = 2) }
                        _effect.send(SignUpEffect.NavigateToCompleteProfile)
                    }
                }
            }
            .collect()
    }

    private fun resendOtp() = viewModelScope.launch {
        executeAsFlow { resendOtpSignUpUseCase() }
            .onStart {
                updateState { copy(showLoading = true) }
            }
            .onEach {
                updateState { copy(showLoading = false) }
                when (it) {
                    is Response.Error -> {
                        updateState { copy(otpSentAlertSuccess = false) }
                    }

                    is Response.Result -> {
                        onCountDownStart()
                        updateState {
                            copy(
                                otpSentCountDown = 120_000L,
                                otpSentAlertSuccess = true
                            )
                        }
                        _effect.send(SignUpEffect.NavigateToCompleteProfile)
                    }
                }
            }
            .collect()
    }
}