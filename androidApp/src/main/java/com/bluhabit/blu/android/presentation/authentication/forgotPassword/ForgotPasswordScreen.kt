/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.forgotPassword

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bluhabit.blu.android.presentation.authentication.forgotPassword.screen.CompleteForgotPasswordScreen
import com.bluhabit.blu.android.presentation.authentication.forgotPassword.screen.InputForgotPasswordScreen
import com.bluhabit.blu.android.presentation.authentication.forgotPassword.screen.InputNewPasswordScreen
import com.bluhabit.blu.android.presentation.authentication.forgotPassword.screen.OtpForgotPasswordScreen
import com.bluhabit.core.ui.theme.UwangTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun ForgotPasswordScreen(
    navHostController: NavHostController = rememberNavController(),
    stateFlow: Flow<ForgotPasswordState> = flowOf(),
    effectFlow: Flow<ForgotPasswordEffect> = flowOf(),
    onAction: (ForgotPasswordAction) -> Unit = {},
) {
    val state by stateFlow.collectAsStateWithLifecycle(initialValue = ForgotPasswordState())
    val effect by effectFlow.collectAsState(initial = ForgotPasswordEffect.None)
    LaunchedEffect(key1 = effect, block = {
        when (effect) {
            ForgotPasswordEffect.None -> Unit
        }
    })
    fun goBack() {
        if (state.currentScreen > 0) {
            onAction(ForgotPasswordAction.OnScreenChange(state.currentScreen - 1))
        } else {
            navHostController.navigateUp()
        }
    }
    BackHandler {
        goBack()
    }
    when (state.currentScreen) {
        0 -> InputForgotPasswordScreen(
            state = state,
            onBackPressed = {
                goBack()
            },
            onAction=onAction
        )

        1 -> OtpForgotPasswordScreen(
            state = state,
            onBackPressed = {
                goBack()
            },
            onAction=onAction
        )

        2 -> InputNewPasswordScreen(
            state = state,
            onBackPressed = {
                goBack()
            },
            onAction=onAction
        )

        3 -> CompleteForgotPasswordScreen(
            onConfirm = {
                navHostController.navigateUp()
            }
        )
    }
}

@Preview
@Composable
fun ForgotPasswordScreenPreview() {
    UwangTheme {
        ForgotPasswordScreen()
    }
}