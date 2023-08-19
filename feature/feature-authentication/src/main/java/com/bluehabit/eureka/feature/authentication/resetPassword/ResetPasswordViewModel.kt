/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.resetPassword

import android.util.Log
import androidx.lifecycle.SavedStateHandle
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
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
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
            Log.e("HEHE",deepLinkParameter())
        }
    }

    private fun deepLinkParameter() = savedStateHandle.get<String>(Routes.ResetPassword.argDeeplink).orEmpty()

    private fun linkConfirmation() = async {
        executeAsFlow { linkConfirmationUseCase(token = deepLinkParameter()) }
            .collect {
                when (it) {
                    is Response.Error -> Unit
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
                            isLoading = false
                        )
                    }

                    Response.Loading -> commit {
                        copy(
                            isLoading = true
                        )
                    }

                    is Response.Result -> commit {
                        copy(
                            isLoading = false,
                            currentScreen = AUTH_SCREEN_INSTRUCTION_RESET_PASSWORD
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
                            isLoading = false
                        )
                    }

                    Response.Loading -> commit {
                        copy(
                            isLoading = true
                        )
                    }

                    is Response.Result -> commit {
                        copy(
                            isLoading = false,
                            currentScreen = AuthConstant.AUTH_SCREEN_RESET_SUCCESS
                        )
                    }
                }
            }
    }

    override fun onAction(action: ResetPasswordAction) {
        when (action) {
            ResetPasswordAction.SubmitCreateNewPassword -> setNewPassword()
            ResetPasswordAction.SubmitRequestResetPassword -> requestResetPassword()
        }
    }
}