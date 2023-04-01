/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.signIn

import android.util.Patterns
import com.bluehabit.budgetku.android.base.BaseViewModel
import com.bluehabit.budgetku.data.common.Response
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
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> showSnackbar("Email didn't valid")
            email.isEmpty() || password.isEmpty() -> showSnackbar("Email or password cannot emmpty")
            else -> valid(email, password)
        }
    }

    private fun handelResponse(response: Response<String>) = async {
        when (response) {
            is Response.Error -> showSnackbar(response.errorMessage())
            Response.Loading -> showSnackbar("Loading")
            is Response.Result -> showSnackbar("Sukses")
        }
    }

    override fun handleActions() = onEvent {
        when (it) {
            SignInEvent.SignInWithEmail -> validateData { email, password ->
                signInWithEmailUseCase(email, password).collect(::handelResponse)
            }

            is SignInEvent.SignInWithGoogle ->
                signInWIthGoogleUseCase(it.result?.await()?.idToken).collect(::handelResponse)
        }
    }
}


