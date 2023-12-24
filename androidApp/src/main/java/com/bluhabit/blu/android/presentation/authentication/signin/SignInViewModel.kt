/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signin

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.bluhabit.blu.android.common.BaseViewModel
import com.bluhabit.blu.android.data.authentication.domain.SignInBasicUseCase
import com.bluhabit.blu.android.data.authentication.domain.SignInGoogleUseCase
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
    private val signInBasicUseCase: SignInBasicUseCase
) : BaseViewModel<SignInState, SignInAction, SignInEffect>(
    SignInState()
) {
    override fun onAction(action: SignInAction) {
        when (action) {
            is SignInAction.OnEmailChange -> updateState { copy(emailState = action.value) }
            is SignInAction.OnPasswordChange -> updateState {
                copy(
                    passwordState = action.value,
                    passwordVisibility = action.visibility
                )
            }

            is SignInAction.OnSignInBasic -> signInBasic()
            is SignInAction.OnSignInGoogle -> signInGoogle(action.authResult)
            is SignInAction.OtpNumberAction -> updateState {
                copy(
                    otpNumberState = action.value,
                )
            }
        }
    }

    private fun signInBasic() = viewModelScope.launch {
        executeAsFlow { signInBasicUseCase(email = state.value.emailState) }
            .onStart { }
            .onEach {
                when (it) {
                    is Response.Error -> Unit
                    is Response.Result -> {
                        sendEffect(SignInEffect.NavigateToOtp)
                    }
                }
            }
            .collect()
    }

    private fun signInGoogle(task: Task<GoogleSignInAccount>) = viewModelScope.launch {
        val auth = task.await()
        executeAsFlow { signInGoogleUseCase(auth.idToken.orEmpty()) }
            .onStart { }
            .onEach { }
            .collect()
    }
}