/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.signIn

import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.eureka.data.authentication.domain.SignInWithEmailUseCase
import com.bluehabit.eureka.data.common.executeAsFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
) : MviViewModel<SignInState, SignInIntent, SignInAction>(SignInState()) {

    private fun signInWithEmail() = asyncWithState {
        executeAsFlow { signInWithEmailUseCase(email, password) }.onEach {}
    }

    override fun onAction(action: SignInAction) {
        when (action) {
            SignInAction.SignInWithEmail -> signInWithEmail()
            is SignInAction.OnEmailChange -> {}
            is SignInAction.OnPasswordChange -> {}
            is SignInAction.SignInWithGoogle -> {}
        }
    }
}


