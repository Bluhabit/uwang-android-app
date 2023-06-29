/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.signIn

import android.util.Patterns
import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.budgetku.data.domain.auth.SignInWIthGoogleUseCase
import com.bluehabit.budgetku.data.domain.auth.SignInWithEmailUseCase
import com.bluehabit.budgetku.data.remote.dummy.dummyUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val signInWIthGoogleUseCase: SignInWIthGoogleUseCase
) : MviViewModel<SignInState,SignInIntent, SignInAction>(SignInState()) {

    private fun validateData(
        valid: suspend (String, String) -> Unit
    ) = asyncWithState {
     //   controller.context.hideKeyboard()
        when {
            email.isEmpty() || password.isEmpty() -> {
                commit { copy(emailIsError = email.isEmpty(), passwordIsError = password.isEmpty()) }
            }

            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                commit { copy(emailIsError = true) }
            }

            email != dummyUser.email && password != dummyUser.password -> {
            //    controller.showSnackbar("Akun tidak ditemukan")
            }

            else -> {
                valid(email, password)
            }
        }
    }


    override fun onAction(action: SignInAction) {
        when (action) {
            SignInAction.SignInWithEmail -> validateData { email, password ->
                signInWithEmailUseCase(email, password)
//                    .onEach(
//                    loading = {
//                       // controller.showSnackbar("Loading")
//                              },
//                    error = {
//                        //controller.showSnackbar(it)
//                            },
//                    success = {
//                        //controller.showSnackbar(it.userEmail)
//                    }
//                )
            }

            is SignInAction.OnEmailChange -> commit {
                copy(
                    email = action.email,
                    emailIsError = (!Patterns.EMAIL_ADDRESS.matcher(action.email).matches() or action.email.isEmpty())
                )
            }

            is SignInAction.OnPasswordChange -> commit {
                copy(password = action.password, passwordIsError = action.password.isEmpty())
            }

            is SignInAction.SignInWithGoogle ->async {
                signInWIthGoogleUseCase(action.result?.await()?.idToken)
//                    .onEach(
//                        loading = {
//                            //controller.showSnackbar("Loading")
//                        },
//                        error = {
//                            // controller.showSnackbar(it)
//                        },
//                        success = {
//                            // controller.showSnackbar(it.userEmail)
//                        }
//                    )
            }
        }
    }
}


