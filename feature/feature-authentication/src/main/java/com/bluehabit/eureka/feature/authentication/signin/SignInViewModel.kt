/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.signin

import app.trian.mvi.ui.viewModel.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
) : MviViewModel<SignInState, SignInAction>(
    SignInState()
) {
    private fun signIn()=async {

    }
    override fun onAction(action: SignInAction) {
        when (action) {
            SignInAction.Nothing -> Unit
            SignInAction.Submit -> {

            }
        }
    }
}