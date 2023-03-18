/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.signUp

import com.bluehabit.budgetku.android.base.BaseViewModel
import com.bluehabit.budgetku.sdk.auth.AuthSDK
import com.bluehabit.budgetku.utils.Response
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authSDK: AuthSDK
) : BaseViewModel<SignUpState, SignUpEvent>(SignUpState()) {

    init {
        handleEvent()
    }

    private fun signUpWithEmail(
    ) = async {
        authSDK.signUpWithEmail(
            uiState.value.fullName, uiState.value.email, uiState.value.password
        ).collect {
            when (it) {
                is Response.Error -> app.showSnackbar(it.message)
                Response.Loading -> Unit
                is Response.Result -> app.showSnackbar(it.data.message)
            }
        }
    }


    private fun signUpGoogle(
        result: Task<GoogleSignInAccount>?
    ) = async {
        if (result != null) {
            val token = result.await()
            authSDK.signUpGoogle(token.idToken.orEmpty()).collect {
                when (it) {
                    is Response.Error -> app.showSnackbar(it.message)
                    Response.Loading -> Unit
                    is Response.Result -> app.showSnackbar(it.data.message)
                }
            }
        } else {
            app.showSnackbar("Cancel by provider")
        }

    }


    override fun handleEvent() = onEvent {
        when (it) {
            is SignUpEvent.SetEmail -> {
                updateState(
                    uiState.value.copy(
                        email = it.email
                    )
                )
            }
            is SignUpEvent.SetFullName -> {
                updateState(
                    uiState.value.copy(
                        fullName = it.fullName
                    )
                )
            }
            is SignUpEvent.SetPassword -> {
                updateState(
                    uiState.value.copy(
                        password = it.password
                    )
                )
            }
            SignUpEvent.SignUpWithEmail -> signUpWithEmail()
            is SignUpEvent.SignUpWithGoogle -> signUpGoogle(it.result)
        }
    }
}