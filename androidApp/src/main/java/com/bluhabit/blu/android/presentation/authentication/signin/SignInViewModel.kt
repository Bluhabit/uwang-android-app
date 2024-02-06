/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signin

import android.os.CountDownTimer
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.bluhabit.blu.android.common.BaseViewModel
import com.bluhabit.blu.android.data.authentication.domain.ResendOtpSignInUseCase
import com.bluhabit.blu.android.data.authentication.domain.SignInBasicUseCase
import com.bluhabit.blu.android.data.authentication.domain.SignInGoogleUseCase
import com.bluhabit.blu.android.data.authentication.domain.VerifyOtpSignInBasicUseCase
import com.bluhabit.blu.data.common.Response
import com.bluhabit.blu.data.common.executeAsFlow
import com.bluhabit.core.ui.components.textfield.TextFieldState
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
class SignInViewModel @Inject constructor(
    private val signInGoogleUseCase: SignInGoogleUseCase,
    private val signInBasicUseCase: SignInBasicUseCase,
    private val resendOtpSignInUseCase: ResendOtpSignInUseCase,
    private val verifyOtpSignInBasicUseCase: VerifyOtpSignInBasicUseCase
) : BaseViewModel<SignInState, SignInAction, SignInEffect>(
    SignInState()
) {
    private var countDownTimer: CountDownTimer? = null

    override fun onAction(action: SignInAction) {
        when (action) {
            is SignInAction.OnEmailChange -> onEmailChange(action.value)
            is SignInAction.OnPasswordChange -> onPasswordChange(action.value)
            is SignInAction.OnSignInBasic -> signInBasic()
            is SignInAction.OnSignInGoogle -> signInGoogle(action.authResult)
            is SignInAction.OnOtpChange -> {
                updateState { copy(otpNumberState = action.value, otpNumberInputState = TextFieldState.None) }
                if (action.value.length == 4) {
                    verifyOtp()
                }
            }

            is SignInAction.OnPasswordVisibilityChange -> updateState { copy(passwordVisibility = action.visibility) }
            SignInAction.OnVerifyOtp -> verifyOtp()
            is SignInAction.OnScreenChange -> updateState { copy(currentScreen = action.screen) }
            is SignInAction.OnSentOtpAlertVisibilityChange -> updateState { copy(otpSentAlertVisibility = action.visibility) }
            SignInAction.OnCountDownStart -> onCountDownStart()
            SignInAction.OnResentOtp -> resendOtpSignIn()
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
                    updateState { copy(showButtonResendOtp=true) }
                    countDownTimer?.cancel()
                }
            }
            updateState { copy(showButtonResendOtp=false) }
            countDownTimer?.start()
        }
    }

    override fun onCleared() {
        super.onCleared()
        countDownTimer?.cancel()
        _state.value = SignInState()
    }

    private fun onPasswordChange(password: String) {
        val isPasswordValid = password.isNotEmpty()
        updateState {
            copy(
                passwordState = password,
                passwordInputState = if (isPasswordValid) TextFieldState.None else TextFieldState.Error("Email atau password tidak valid")
            )
        }
    }

    private fun onEmailChange(email: String) {
        val emailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val inputState = when {
            email.length > 1 && emailValid -> TextFieldState.None
            else -> TextFieldState.Error("Email atau password tidak valid")
        }
        updateState {
            copy(emailState = email, emailInputState = inputState)
        }
    }

    private fun signInBasic() = viewModelScope.launch {
        executeAsFlow {
            signInBasicUseCase(
                email = state.value.emailState,
                password = state.value.passwordState
            )
        }
            .onStart {
                updateState { copy(showLoading = true) }
            }
            .onEach {
                updateState { copy(showLoading = false) }
                when (it) {
                    is Response.Error -> {
                        updateState {
                            copy(
                                passwordInputState = TextFieldState.Error(it.message),
                            )
                        }
                    }

                    is Response.Result -> {
                        updateState { copy(currentScreen = 1) }
                    }
                }
            }
            .collect()
    }

    private fun verifyOtp() = viewModelScope.launch {
        executeAsFlow { verifyOtpSignInBasicUseCase(state.value.otpNumberState) }
            .onStart {
                updateState { copy(showLoading = true) }
            }
            .onEach {
                updateState { copy(showLoading = false) }
                when (it) {
                    is Response.Error -> {
                        val otp = _state.value.otpAttempt
                        val attempt = (otp + 1)
                        updateState {
                            copy(
                                otpNumberInputState = TextFieldState.Error(it.message),
                                otpAttempt = attempt
                            )
                        }
                    }

                    is Response.Result -> {
                        if (it.data.user.profile.isEmpty()) {
                            sendEffect(SignInEffect.NavigateToPersonalize)
                        } else {
                            sendEffect(SignInEffect.NavigateToMain)
                        }
                    }
                }
            }
            .collect()
    }

    private fun resendOtpSignIn() = viewModelScope.launch {
        executeAsFlow { resendOtpSignInUseCase() }
            .onStart {
                updateState { copy(showLoading = true) }
            }
            .onEach {
                updateState { copy(showLoading = false) }
                when (it) {
                    is Response.Error -> {
                        updateState { copy(otpSentAlertSuccess = false, otpSentAlertVisibility = true) }
                    }

                    is Response.Result -> {
                        updateState { copy(otpSentAlertSuccess = true, otpSentCountDown = 120_000L, otpSentAlertVisibility = true) }
                        onCountDownStart()
                    }
                }
            }
            .collect()

    }

    private fun signInGoogle(task: Task<GoogleSignInAccount>) = viewModelScope.launch {
        val auth = task.await()
        executeAsFlow { signInGoogleUseCase(auth.idToken.orEmpty()) }
            .onStart {
                updateState { copy(showLoading = true) }
            }
            .onEach {
                updateState { copy(showLoading = false) }
                when (it) {
                    is Response.Error -> Unit
                    is Response.Result -> {
                        if (it.data.credential.profile.isEmpty()) {
                            sendEffect(SignInEffect.NavigateToPersonalize)
                        } else {
                            sendEffect(SignInEffect.NavigateToMain)
                        }
                    }
                }
            }
            .collect()
    }
}