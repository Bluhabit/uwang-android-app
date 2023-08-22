/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.signUp

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import app.trian.mvi.ui.extensions.Empty
import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.eureka.data.authentication.domain.CompleteProfileUseCase
import com.bluehabit.eureka.data.authentication.domain.OtpConfirmationUseCase
import com.bluehabit.eureka.data.common.Response
import com.bluehabit.eureka.data.common.executeAsFlow
import com.bluehabit.eureka.feature.authentication.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val savedStateHandle: SavedStateHandle,
    private val otpConfirmationUseCase: OtpConfirmationUseCase,
    private val completeProfileUseCase: CompleteProfileUseCase
) : MviViewModel<SignUpState, SignUpAction>(
    SignUpState()
) {
    init {
        checkCurrentScreen()
    }

    private fun getCurrentScreen() = savedStateHandle.get<Int>(Routes.SignUp.currentScreenArg) ?: 1

    private fun checkCurrentScreen() = async {
        commit { copy(currentScreen = getCurrentScreen()) }
    }

    private fun onSubmitOtp() = asyncWithState {
        executeAsFlow { otpConfirmationUseCase(otp) }
            .collect {
                when (it) {
                    is Response.Error -> commit {
                        copy(
                            isLoading = false,
                            isAlertError = true,
                            effect = SignUpEffect.ShowAlert(it.message)
                        )
                    }

                    Response.Loading -> commit {
                        copy(isLoading = true)
                    }

                    is Response.Result -> {
                        commit {
                            copy(
                                otp = String.Empty,
                                showDialogConfirmation = true,
                                isLoading = false,
                                isAlertError = false,
                                effect = SignUpEffect.ShowAlert("Success")
                            )
                        }
                    }
                }
            }
    }

    private fun onSubmitCompleteProfile() = asyncWithState {
        executeAsFlow {
            completeProfileUseCase(
                fullName = fullName,
                password = password
            )
        }.collect {
            when (it) {
                is Response.Error -> commit {
                    copy(isLoading = false)
                }

                Response.Loading -> commit {
                    copy(isLoading = true)
                }

                is Response.Result -> {
                    commit {
                        copy(
                            fullName = String.Empty,
                            password = String.Empty,
                            confirmPassword = String.Empty,
                            effect = SignUpEffect.NavigateToHome,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    private fun changeAndValidateFullName(fullName: String) = async {
        when {
            (fullName.length < 3) || (fullName.length > 70) -> commit {
                copy(
                    fullName = fullName,
                    fullNameError = true,
                    fullNameErrorMessage = context.getString(R.string.text_input_fullname_error_complete_profile)
                )
            }

            else -> commit {
                copy(
                    fullName = fullName,
                    fullNameError = false,
                    fullNameErrorMessage = String.Empty
                )
            }
        }
    }

    private fun changeAndValidatePassword(password: String) = async {
        when {
            (password.length < 8) -> commit {
                copy(
                    password = password,
                    passwordError = true,
                    passwordErrorMessage = context.getString(R.string.text_input_password_error_complete_profile)
                )
            }

            else -> commit {
                copy(
                    password = password,
                    passwordError = false,
                    passwordErrorMessage = String.Empty
                )
            }
        }
    }

    private fun changeAndValidateConfirmPassword(confirmPassword: String) = asyncWithState {
        when {
            confirmPassword != password -> commit {
                copy(
                    confirmPassword = confirmPassword,
                    confirmPasswordError = true,
                    confirmPasswordErrorMessage = context.getString(R.string.text_input_confirm_password_error_complete_profile)
                )
            }

            else -> commit {
                copy(
                    confirmPassword = confirmPassword,
                    confirmPasswordError = false,
                    confirmPasswordErrorMessage = String.Empty
                )
            }
        }
    }

    override fun onAction(action: SignUpAction) {
        when (action) {
            SignUpAction.Nothing -> Unit
            SignUpAction.SubmitOtp -> onSubmitOtp()
            SignUpAction.SubmitCompleteProfile -> onSubmitCompleteProfile()
            is SignUpAction.OnConfirmPasswordChange -> changeAndValidateConfirmPassword(action.value)
            is SignUpAction.OnFullNameChange -> changeAndValidateFullName(action.value)
            is SignUpAction.OnPasswordChange -> changeAndValidatePassword(action.value)
        }
    }
}