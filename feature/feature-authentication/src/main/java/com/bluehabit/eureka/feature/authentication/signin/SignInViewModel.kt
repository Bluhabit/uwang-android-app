/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.signin

import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.eureka.data.authentication.domain.CheckSessionUseCase
import com.bluehabit.eureka.data.authentication.domain.SignInWithEmailUseCase
import com.bluehabit.eureka.data.common.Response
import com.bluehabit.eureka.data.common.executeAsFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val checkSessionUseCase: CheckSessionUseCase
) : MviViewModel<SignInState, SignInAction>(
    SignInState()
) {
    private fun checkIfUserLoggedIn() = async {
        executeAsFlow { checkSessionUseCase() }
            .collect {
                when (it) {
                    is Response.Error -> Unit
                    Response.Loading -> Unit
                    is Response.Result -> {
                        if (it.data) {
                            navigate(Routes.Home.routeName)
                        }
                    }
                }
            }
    }

    private fun signIn() = asyncWithState {
        executeAsFlow {
            signInWithEmailUseCase(
                email = email,
                password = password
            )
        }.collect {
            when (it) {
                is Response.Error -> commit { copy(effect = SignInEffect.ShowDialog(it.message)) }
                Response.Loading -> Unit
                is Response.Result -> navigate(Routes.Home.routeName)
            }
        }
    }

    override fun onAction(action: SignInAction) {
        when (action) {
            SignInAction.Nothing -> Unit
            SignInAction.Submit -> signIn()
            SignInAction.CheckSession -> checkIfUserLoggedIn()
        }
    }
}