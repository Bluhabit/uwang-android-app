/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signin

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
    override fun onAction(action: SignInAction) {
        when (action) {
            is SignInAction.OnEmailChange -> onEmailChange(action.value)
            is SignInAction.OnPasswordChange -> onPasswordChange(action.value)
            is SignInAction.OnSignInBasic -> signInBasic()
            is SignInAction.OnSignInGoogle -> signInGoogle(action.authResult)
            is SignInAction.OtpNumberAction -> updateState { copy(otpNumberState = action.value) }

            is SignInAction.OnPasswordVisibilityChange -> updateState {
                copy(passwordVisibility = action.visibility)
            }

            SignInAction.OnVerifyOtp -> verifyOtp()
            is SignInAction.OnScreenChange -> updateState { copy(currentScreen = action.screen) }
        }
    }

    private fun onPasswordChange(password: String) {
        updateState { copy(passwordState = password, passwordError = password.isEmpty()) }
    }

    private fun onEmailChange(email: String) {
        val emailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        updateState {
            copy(
                emailState = email,
                emailError = !emailValid
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
            .onStart { }
            .onEach {
                when (it) {
                    is Response.Error -> Unit
                    is Response.Result -> {
                        //go to otp
                        updateState { copy(currentScreen = 1) }
                    }
                }
            }
            .collect()
    }

    private fun verifyOtp() = viewModelScope.launch {
        val otp = state.value.otpNumberState
        executeAsFlow { verifyOtpSignInBasicUseCase(otp) }
            .onStart { }
            .onEach {
                when (it) {
                    is Response.Error -> Unit
                    is Response.Result -> {
                        if (it.data.credential.profile.isEmpty()) {
                            _effect.send(SignInEffect.NavigateToCompleteProfile)
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
            .onStart { }
            .onEach {
                when (it) {
                    is Response.Error -> Unit
                    is Response.Result -> {
                        if (it.data.credential.profile.isEmpty()) {
                            _effect.send(SignInEffect.NavigateToCompleteProfile)
                        } else {
                            _effect.send(SignInEffect.NavigateToMain)
                        }
                    }
                }
            }
            .collect()
    }
}