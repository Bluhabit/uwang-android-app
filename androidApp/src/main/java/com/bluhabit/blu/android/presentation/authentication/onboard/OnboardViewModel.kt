/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.onboard

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.bluhabit.blu.android.common.BaseViewModel
import com.bluhabit.blu.android.common.event.EventObserverImpl
import com.bluhabit.blu.android.common.event.EventPublisher
import com.bluhabit.blu.android.data.authentication.domain.CheckSessionUseCase
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
class OnboardViewModel @Inject constructor(
    private val checkSessionUseCase: CheckSessionUseCase,
    private val signInGoogleUseCase: SignInGoogleUseCase
) : BaseViewModel<OnboardState, OnboardAction, OnboardEffect>(
    OnboardState()
) {
    override fun onAction(action: OnboardAction) {
        viewModelScope.launch {
            when (action) {
                OnboardAction.CheckSession -> {
                    if (checkSessionUseCase()) {
                        _effect.send(OnboardEffect.NavigateHome)
                    } else {
                        _effect.send(OnboardEffect.NavigateAuth)
                    }
                }

                is OnboardAction.SignInGoogle -> signInGoogle(action.task)
            }
        }
    }

    private suspend fun signInGoogle(task: Task<GoogleSignInAccount>) = viewModelScope.launch {
        val auth = task.await()
        executeAsFlow { signInGoogleUseCase(auth.idToken.orEmpty()) }
            .onStart { }
            .onEach {
                when (it) {
                    is Response.Error -> Unit
                    is Response.Result -> {
                        if (it.data.credential.profile.isEmpty()) {
                            _effect.send(OnboardEffect.NavigateCompleteProfile)
                        } else {
                            _effect.send(OnboardEffect.NavigateHome)
                        }
                    }
                }
            }
            .collect()
    }
}