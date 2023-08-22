/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.resetPassword

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.SavedStateHandle
import app.trian.mvi.ui.extensions.Empty
import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.eureka.data.authentication.AuthConstant
import com.bluehabit.eureka.data.authentication.AuthConstant.AUTH_SCREEN_CREATE_PASSWORD
import com.bluehabit.eureka.data.authentication.AuthConstant.AUTH_SCREEN_INSTRUCTION_RESET_PASSWORD
import com.bluehabit.eureka.data.authentication.AuthConstant.AUTH_SCREEN_LINK_CONFIRMATION
import com.bluehabit.eureka.data.authentication.domain.LinkConfirmationUseCase
import com.bluehabit.eureka.data.authentication.domain.RequestResetPasswordUseCase
import com.bluehabit.eureka.data.authentication.domain.SetNewPasswordUseCase
import com.bluehabit.eureka.data.common.Response
import com.bluehabit.eureka.data.common.executeAsFlow
import com.bluehabit.eureka.feature.authentication.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val savedStateHandle: SavedStateHandle,
    private val requestResetPasswordUseCase: RequestResetPasswordUseCase,
    private val setNewPasswordUseCase: SetNewPasswordUseCase,
    private val linkConfirmationUseCase: LinkConfirmationUseCase
) : MviViewModel<ResetPasswordState, ResetPasswordAction>(
    ResetPasswordState()
) {
    init {
        if (deepLinkParameter().isNotEmpty()) {
            commit { copy(currentScreen = AUTH_SCREEN_LINK_CONFIRMATION) }
            linkConfirmation()
        }
    }

    private fun deepLinkParameter() = savedStateHandle.get<String>(Routes.ResetPassword.argDeeplink).orEmpty()

    private fun linkConfirmation() = async {
        executeAsFlow { linkConfirmationUseCase(token = deepLinkParameter()) }
            .collect {
                when (it) {
                    is Response.Error -> commit { copy(effect = ResetPasswordEffect.ShowAlert(it.message)) }
                    Response.Loading -> Unit
                    is Response.Result -> {
                        commit {
                            copy(
                                currentScreen = AUTH_SCREEN_CREATE_PASSWORD,
                                isLoading = false
                            )
                        }
                    }
                }
            }
    }

    private fun requestResetPassword() = asyncWithState {
        executeAsFlow { requestResetPasswordUseCase(email = email) }
            .collect {
                when (it) {
                    is Response.Error -> commit {
                        copy(
                            isLoading = false,
                            emailError = true,
                            emailErrorMessage = String.Empty,
                            effect = ResetPasswordEffect.ShowAlert(it.message)
                        )
                    }

                    Response.Loading -> commit {
                        copy(
                            isLoading = true,
                            emailError = false,
                            emailErrorMessage = String.Empty
                        )
                    }

                    is Response.Result -> commit {
                        copy(
                            isLoading = false,
                            currentScreen = AUTH_SCREEN_INSTRUCTION_RESET_PASSWORD,
                            emailError = false,
                            emailErrorMessage = String.Empty
                        )
                    }
                }
            }
    }

    private fun setNewPassword() = asyncWithState {
        executeAsFlow { setNewPasswordUseCase(password = password) }
            .collect {
                when (it) {
                    is Response.Error -> commit {
                        copy(
                            isLoading = false,
                            passwordError = true,
                            passwordErrorMessage = String.Empty,
                            confirmPasswordError = true,
                            confirmPasswordErrorMessage = String.Empty,
                            effect = ResetPasswordEffect.ShowAlert(it.message)
                        )
                    }

                    Response.Loading -> commit {
                        copy(
                            isLoading = true,
                            passwordError = false,
                            passwordErrorMessage = String.Empty,
                            confirmPasswordError = false,
                            confirmPasswordErrorMessage = String.Empty
                        )
                    }

                    is Response.Result -> commit {
                        copy(
                            isLoading = false,
                            currentScreen = AuthConstant.AUTH_SCREEN_RESET_SUCCESS,
                            passwordError = false,
                            passwordErrorMessage = String.Empty,
                            confirmPasswordError = false,
                            confirmPasswordErrorMessage = String.Empty
                        )
                    }
                }
            }
    }

    private fun changeAndValidateEmail(email: String) = async {
        commit { copy(email = email) }
        when {
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> commit {
                copy(
                    emailError = true,
                    emailErrorMessage = String.Empty
                )
            }

            else -> commit {
                copy(
                    emailError = false,
                    emailErrorMessage = String.Empty
                )
            }
        }
    }

    private fun changeAndValidatePassword(password: String) = async {
        commit { copy(password = password) }
        when {
            password.length < 8 -> commit {
                copy(
                    passwordError = true,
                    passwordErrorMessage = context.getString(R.string.text_input_password_error_reset_password)
                )
            }

            else -> commit {
                copy(
                    passwordError = false,
                    passwordErrorMessage = String.Empty
                )
            }
        }
    }

    private fun changeAndValidateConfirmPassword(confirmPassword: String) = asyncWithState {
        commit { copy(confirmPassword = confirmPassword) }
        when {
            confirmPassword != password -> commit {
                copy(
                    confirmPasswordError = true,
                    confirmPasswordErrorMessage = context.getString(R.string.text_input_confirm_password_error_reset_password)
                )
            }

            else -> commit {
                copy(
                    confirmPasswordError = false,
                    confirmPasswordErrorMessage = String.Empty
                )
            }
        }
    }

    override fun onAction(action: ResetPasswordAction) {
        when (action) {
            ResetPasswordAction.SubmitCreateNewPassword -> setNewPassword()
            ResetPasswordAction.SubmitRequestResetPassword -> requestResetPassword()
            is ResetPasswordAction.OnConfirmPasswordChange -> changeAndValidateConfirmPassword(action.value)
            is ResetPasswordAction.OnEmailChange -> changeAndValidateEmail(action.value)
            is ResetPasswordAction.OnPasswordChange -> changeAndValidatePassword(action.value)
        }
    }
}