/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.auth

import app.trian.mvi.ui.extensions.Empty
import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.eureka.data.authentication.domain.SignInWithEmailUseCase
import com.bluehabit.eureka.data.authentication.domain.SignInWithGoogleUseCase
import com.bluehabit.eureka.data.authentication.domain.SignUpWithEmailUseCase
import com.bluehabit.eureka.data.common.Response
import com.bluehabit.eureka.data.common.executeAsFlow
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase,
) : MviViewModel<AuthState, AuthAction>(
    AuthState()
) {

    private fun signInWithEmail() = asyncWithState {
        executeAsFlow {
            signInWithEmailUseCase(
                email = emailSignIn,
                password = passwordSignIn
            )
        }.collect {
            when (it) {
                is Response.Error -> commit {
                    copy(
                        isLoading = false,
                        isError = true,
                        emailSignInError = true,
                        passwordSignInError = true,
                        errorMessage = it.message
                    )
                }

                Response.Loading -> commit {
                    copy(
                        isLoading = true,
                        emailSignInError = false,
                        passwordSignInError = false
                    )
                }

                is Response.Result -> commit {
                    copy(
                        emailSignIn = String.Empty,
                        passwordSignIn = String.Empty,
                        isLoading = false,
                        emailSignInError = false,
                        passwordSignInError = false,
                        effect = AuthEffect.NavigateToHome,
                    )
                }
            }
        }
    }

    private fun signInWithGoogle(
        task: Task<GoogleSignInAccount>
    ) = async {
        commit { copy(isLoading = true) }
        val result = task.await()
        executeAsFlow { signInWithGoogleUseCase(token = result.idToken.orEmpty()) }
            .collect {
                when (it) {
                    is Response.Error -> commit {
                        copy(
                            isLoading = false,
                            isError = true,
                            errorMessage = it.message
                        )
                    }

                    Response.Loading -> commit {
                        copy(
                            isLoading = true
                        )
                    }

                    is Response.Result -> commit {
                        copy(
                            emailSignIn = String.Empty,
                            passwordSignIn = String.Empty,
                            effect = AuthEffect.NavigateToHome,
                            isLoading = false
                        )
                    }
                }
            }
    }

    private fun signUpWithEmail() = asyncWithState {
        executeAsFlow { signUpWithEmailUseCase(emailSignUp) }
            .collect {
                when (it) {
                    is Response.Error -> commit {
                        copy(
                            isLoading = false,
                            isError = true,
                            emailSignUpError = true,
                            errorMessage = it.message
                        )
                    }

                    Response.Loading -> commit {
                        copy(isLoading = true, emailSignUpError = false)
                    }

                    is Response.Result -> commit {
                        copy(
                            emailSignUp = String.Empty,
                            effect = AuthEffect.NavigateToOtp,
                            emailSignUpError = false,
                            isLoading = false
                        )
                    }
                }
            }
    }

    override fun onAction(action: AuthAction) {
        when (action) {
            AuthAction.Nothing -> Unit
            AuthAction.SignInWithEmail -> signInWithEmail()
            AuthAction.SignUpWithEmail -> signUpWithEmail()
            is AuthAction.SignInWithGoogle -> signInWithGoogle(action.value)
        }
    }
}