/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.signIn

import android.util.Patterns
import com.bluehabit.budgetku.android.base.BaseViewModel
import com.bluehabit.budgetku.android.feature.dashboard.home.Home
import com.bluehabit.budgetku.data.domain.auth.SignInWIthGoogleUseCase
import com.bluehabit.budgetku.data.domain.auth.SignInWithEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
                commit { copy(emailIsError = email.isEmpty(), passwordIsError = password.isEmpty()) }
            }

            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                commit { copy(emailIsError = true) }
            }

            else -> valid(email, password)
        }
    }

    override fun handleActions() = onEvent {
        when (it) {
            SignInEvent.SignInWithEmail -> validateData { email, password ->

                navigateAndReplaceAll(Home.routeName)
//                signInWithEmailUseCase(email, password).onEach(
//                    loading = { showSnackbar("Loading") },
//                    error = ::showSnackbar,
//                    success = { showSnackbar(userProfile.userFullName) }
//                )
            }

            is SignInEvent.OnEmailChange -> commit {
                copy(
                    email = it.email,
                    emailIsError = (!Patterns.EMAIL_ADDRESS.matcher(it.email).matches() or it.email.isEmpty())
                )
            }

            is SignInEvent.OnPasswordChange -> commit {
                copy(password = it.password, passwordIsError = it.password.isEmpty())
            }

            is SignInEvent.SignInWithGoogle ->
                navigateAndReplaceAll(Home.routeName)

//                signInWIthGoogleUseCase(it.result?.await()?.idToken).onEach(
//                    loading = { showSnackbar("Loading") },
//                    error = ::showSnackbar,
//                    success = { showSnackbar(userProfile.userFullName) }
//                )
        }
    }
}


