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
import com.bluhabit.blu.android.data.authentication.domain.SignInGoogleUseCase
import com.bluhabit.blu.data.common.executeAsFlow
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.SignInAccount
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
    private val signInGoogleUseCase: SignInGoogleUseCase
) : BaseViewModel<SignInState, SignInAction, SignInEffect>(
    SignInState(
        emailState = ""
    )
) {
    override fun onAction(action: SignInAction) {
        when (action) {
            is SignInAction.EmailAction -> updateState { copy(emailState = action.value) }
            is SignInAction.PasswordAction -> updateState {
                copy(
                    passwordState = action.value,
                    passwordVisibility = action.visibility
                )
            }

            is SignInAction.ButtonAction -> updateState { copy(buttonEnabled = action.enabled) }
            is SignInAction.SignInGoogle -> signInGoogle(action.authResult)
        }
    }

    private fun signInGoogle(task: Task<GoogleSignInAccount>) = viewModelScope.launch {
        val auth = task.await()
        Log.e("TES", "$auth")
        executeAsFlow { signInGoogleUseCase(auth.idToken.orEmpty()) }
            .onStart { }
            .onEach { }
            .collect()
    }
}