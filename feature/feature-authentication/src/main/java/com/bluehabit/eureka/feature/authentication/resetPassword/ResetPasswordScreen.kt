/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.resetPassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import app.trian.mvi.DeepLink
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.contract.UIContract
import com.bluehabit.core.ui.components.alert.AlertError
import com.bluehabit.core.ui.components.alert.AlertSuccess
import com.bluehabit.core.ui.components.dialog.DialogLoading
import com.bluehabit.core.ui.components.snackbar.BaseSnackbar
import com.bluehabit.core.ui.ext.openEmail
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.eureka.data.authentication.AuthConstant.AUTH_SCREEN_CREATE_PASSWORD
import com.bluehabit.eureka.data.authentication.AuthConstant.AUTH_SCREEN_INSTRUCTION_RESET_PASSWORD
import com.bluehabit.eureka.data.authentication.AuthConstant.AUTH_SCREEN_LINK_CONFIRMATION
import com.bluehabit.eureka.data.authentication.AuthConstant.AUTH_SCREEN_RESET_PASSWORD
import com.bluehabit.eureka.data.authentication.AuthConstant.AUTH_SCREEN_RESET_SUCCESS
import com.bluehabit.eureka.feature.authentication.resetPassword.screen.ScreenCompleteResetPassword
import com.bluehabit.eureka.feature.authentication.resetPassword.screen.ScreenInputNewPassword
import com.bluehabit.eureka.feature.authentication.resetPassword.screen.ScreenInstructionReset
import com.bluehabit.eureka.feature.authentication.resetPassword.screen.ScreenRequestResetPassword
import com.bluehabit.eureka.feature.authentication.resetPassword.screen.ScreenVerifyLink

@Navigation(
    route = Routes.ResetPassword.routeName,
    viewModel = ResetPasswordViewModel::class
)
@DeepLink(
    uri = Routes.ResetPassword.deepLink
)
@DeepLink(
    uri = Routes.ResetPassword.deepLink2
)
@DeepLink(
    uri = Routes.ResetPassword.deepLink3
)
@Composable
fun ResetPasswordScreen(
    uiContract: UIContract<ResetPasswordState, ResetPasswordAction>
) = UIWrapper(uiContract = uiContract) {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()

    UseEffect<ResetPasswordEffect>(
        commit = { copy(effect = ResetPasswordEffect.Nothing) },
        onEffect = {
            when (this) {
                ResetPasswordEffect.Nothing -> Unit
                is ResetPasswordEffect.ShowAlert -> {
                    launch {
                        scaffoldState.snackbarHostState.showSnackbar(this.message)
                    }
                }
            }
        }
    )

    DialogLoading(
        show = state.isLoading
    )

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {host->
            BaseSnackbar(host = host, content = {
                AlertError(message = it.message) {}
            })
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            when (state.currentScreen) {
                AUTH_SCREEN_RESET_PASSWORD -> ScreenRequestResetPassword(
                    state = state,
                    onEmailChange = {
                        dispatch(ResetPasswordAction.OnEmailChange(it))
                    },
                    onSendRequest = {
                        dispatch(ResetPasswordAction.SubmitRequestResetPassword)
                    }
                )

                AUTH_SCREEN_INSTRUCTION_RESET_PASSWORD -> ScreenInstructionReset(
                    onOpenEmailApp = {
                        context.openEmail()
                    },
                    onRetry = {
                        commit { copy(currentScreen = AUTH_SCREEN_RESET_PASSWORD) }
                    }
                )

                AUTH_SCREEN_LINK_CONFIRMATION -> ScreenVerifyLink()
                AUTH_SCREEN_CREATE_PASSWORD -> ScreenInputNewPassword(
                    state = state,
                    onPasswordChanged = {
                        dispatch(ResetPasswordAction.OnPasswordChange(it))
                    },
                    onPasswordConfirmationChanged = {
                        dispatch(ResetPasswordAction.OnConfirmPasswordChange(it))
                    },
                    onResetPassword = {
                        dispatch(ResetPasswordAction.SubmitCreateNewPassword)
                    }
                )

                AUTH_SCREEN_RESET_SUCCESS -> ScreenCompleteResetPassword(
                    onConfirm = {
                        navigator.navigateAndReplace(Routes.Auth.routeName)
                    }
                )
            }
        }
    }
}