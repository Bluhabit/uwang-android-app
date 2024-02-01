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
import com.bluhabit.blu.android.data.authentication.domain.SignInBasicUseCase
import com.bluhabit.blu.android.data.authentication.domain.SignInGoogleUseCase
import com.bluhabit.blu.android.data.authentication.domain.VerifyOtpSignInBasicUseCase
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
class SignInViewModel @Inject constructor(
    private val signInGoogleUseCase: SignInGoogleUseCase,
    private val signInBasicUseCase: SignInBasicUseCase,
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
                updateState { copy(otpNumberState = action.value) }
            }

            is SignInAction.OnPasswordVisibilityChange -> updateState {
                copy(passwordVisibility = action.visibility)
            }

            SignInAction.OnVerifyOtp -> verifyOtp()
            is SignInAction.OnScreenChange -> updateState { copy(currentScreen = action.screen) }
            is SignInAction.OnSentOtpAlertVisibilityChange -> updateState { copy(otpSentAlertVisibility = action.visibility) }
            SignInAction.OnCountDownStart -> onCountDownStart()
            SignInAction.OnResentOtp -> reSentOtp()
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
        _state.value = SignInState()
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

    private fun onPasswordChange(password: String) {
        val emailValid = Patterns.EMAIL_ADDRESS.matcher(state.value.emailState).matches()
        val isPasswordValid = password.isNotEmpty()
        updateState {
            copy(
                passwordState = password,
                passwordError = !isPasswordValid,
                passwordErrorText = if (isPasswordValid) "" else "Email/username atau password tidak valid",
                signInButtonEnabled = isPasswordValid && emailValid
            )
        }
    }

    private fun onEmailChange(email: String) {
        val emailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = state.value.passwordState.isNotEmpty()
        updateState {
            copy(
                emailState = email,
                emailError = !emailValid,
                emailErrorText = if (emailValid) "" else "Email/username atau password tidak valid",
                signInButtonEnabled = emailValid && isPasswordValid
            )
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
                        updateState { copy(signInButtonEnabled = true) }
                    }

                    is Response.Result -> {
                        //go to otp
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
                    is Response.Error -> Unit
                    is Response.Result -> {
                        if (it.data.credential.profile.isEmpty()) {
                            _effect.send(SignInEffect.NavigateToPersonalize)
                        } else {
                            _effect.send(SignInEffect.NavigateToMain)
                        }
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
                            _effect.send(SignInEffect.NavigateToPersonalize)
                        } else {
                            _effect.send(SignInEffect.NavigateToMain)
                        }
                    }
                }
            }
            .collect()
    }
}