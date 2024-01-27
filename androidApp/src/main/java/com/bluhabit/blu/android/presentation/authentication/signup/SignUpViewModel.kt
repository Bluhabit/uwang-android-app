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
            is SignUpAction.OnSignInGoogle -> signUpGoogle(action.authResult)
            is SignUpAction.OnSentOtpAlertVisibilityChange -> updateState { copy(otpSentAlertVisibility = action.visibility) }
            SignUpAction.OnCountDownStart -> onCountDownStart()
            SignUpAction.OnResentOtp -> reSentOtp()
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

    private fun reSentOtp() {
        // Ketika hasil response sudah keluar jalankan fungsi
        updateState { copy(otpSentCountDown = 120_000L) }
        onCountDownStart()
        updateState {
            if (true) { // rubah parameter menjadi hasil response
                // Ketika response berhasil
                copy(otpSentAlertSuccess = true)
            } else {
                // Ketika response gagal
                copy(otpSentAlertSuccess = false)
            }
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
                signUpButtonEnabled = isPasswordValid && isConfirmPasswordValid && isEmailValid
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
                signUpButtonEnabled = isPasswordValid && isConfirmPasswordValid && isEmailValid
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
                signUpButtonEnabled = isPasswordValid && isConfirmPasswordValid && isEmailValid
            )
        }
    }

    private fun signUpBasic() = viewModelScope.launch {
        executeAsFlow { signUpBasicUseCase(email = state.value.emailState, password = state.value.passwordState) }
            .onStart {
                updateState {
                    copy(
                        signUpButtonEnabled = false
                    )
                }
            }
            .onEach {
                when (it) {
                    is Response.Error -> {
                        updateState {
                            copy(
                                signUpButtonEnabled = true
                            )
                        }
                    }

                    is Response.Result -> {
                        updateState {
                            copy(
                                currentScreen = 1,
                            )
                        }
                    }
                }
            }.collect()
    }

    private fun verifyOtp() = viewModelScope.launch {
        executeAsFlow { verifyOtpSignUpBasicUseCase(otp = state.value.otpNumberState) }
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