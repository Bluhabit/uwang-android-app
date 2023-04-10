/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.signIn

import android.util.Patterns
import com.bluehabit.budgetku.android.base.BaseViewModel
import com.bluehabit.budgetku.data.domain.auth.SignInWIthGoogleUseCase
import com.bluehabit.budgetku.data.domain.auth.SignInWithEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val signInWIthGoogleUseCase: SignInWIthGoogleUseCase
) : BaseViewModel<SignInState, SignInEvent>(SignInState()) {

    init {
        handleActions()
    }

    private fun validateData(
        valid: suspend (String, String) -> Unit
    ) = asyncWithState {
        when {
            email.isEmpty() || password.isEmpty() -> {
                    emailIsError = email.isEmpty()
                    passwordIsError = password.isEmpty()
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                emailIsError = true
                showSnackbar("Email didn't valid")
            }
            else -> valid(email, password)
        }
    }

    override fun handleActions() = onEvent {
        when (it) {
            SignInEvent.SignInWithEmail -> validateData { email, password ->
                signInWithEmailUseCase(email, password).onEach(
                    loading = { showSnackbar("Loading") },
                    error = ::showSnackbar,
                    success = { showSnackbar(userProfile.userFullName) }
                )
            }

            is SignInEvent.SignInWithGoogle ->
                signInWIthGoogleUseCase(it.result?.await()?.idToken).onEach(
                    loading = { showSnackbar("Loading") },
                    error = ::showSnackbar,
                    success = { showSnackbar(userProfile.userFullName) }
                )
        }
    }
}


