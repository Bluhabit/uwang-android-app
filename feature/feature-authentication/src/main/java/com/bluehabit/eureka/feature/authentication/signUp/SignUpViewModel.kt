/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.signUp

import androidx.lifecycle.SavedStateHandle
import app.trian.mvi.ui.extensions.Empty
import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.eureka.data.authentication.AuthConstant
import com.bluehabit.eureka.data.authentication.domain.CompleteProfileUseCase
import com.bluehabit.eureka.data.authentication.domain.OtpConfirmationUseCase
import com.bluehabit.eureka.data.common.Response
import com.bluehabit.eureka.data.common.executeAsFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
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
                    is Response.Error -> Unit
                    Response.Loading -> Unit
                    is Response.Result -> {
                        commit {
                            copy(
                                otp = String.Empty,
                                showDialogConfirmation = true
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
                is Response.Error -> Unit
                Response.Loading -> Unit
                is Response.Result -> {
                    commit {
                        copy(
                            fullName = String.Empty,
                            password = String.Empty,
                            confirmPassword = String.Empty,
                            effect = SignUpEffect.NavigateToHome
                        )
                    }
                }
            }
        }
    }

    override fun onAction(action: SignUpAction) {
        when (action) {
            SignUpAction.Nothing -> Unit
            SignUpAction.SubmitOtp -> onSubmitOtp()
            SignUpAction.SubmitCompleteProfile -> onSubmitCompleteProfile()
        }
    }
}