/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signup

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bluhabit.blu.android.presentation.authentication.signup.screen.InputSignUpScreen
import com.bluhabit.blu.android.presentation.authentication.signup.screen.OtpSignUpScreen
import com.bluhabit.core.ui.theme.UwangTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun SignUpScreen(
    navHostController: NavHostController = rememberNavController(),
    stateFlow: Flow<SignUpState> = flowOf(),
    effectFlow: Flow<SignUpEffect> = flowOf(),
    onAction: (SignUpAction) -> Unit = {},
) {
    val state by stateFlow.collectAsStateWithLifecycle(initialValue = SignUpState())
    val effect by effectFlow.collectAsState(initial = SignUpEffect.None)

    LaunchedEffect(key1 = effect, block = {
        when (effect) {
            SignUpEffect.None -> Unit
            SignUpEffect.NavigateToCompleteProfile -> {
                navHostController.navigate("complete_profile")
            }
        }
    })
    fun goBack() {
        if (state.currentScreen == 1) {
            onAction(SignUpAction.OnScreenChange(0))
        } else {
            navHostController.navigateUp()
        }
    }

    BackHandler {
        goBack()
    }

    when (state.currentScreen) {
        0 -> InputSignUpScreen(
            state = state,
            onTermAndCondition = {
                navHostController.navigate("term_and_condition")
            },
            onBackPressed = {
                goBack()
            },
            onSignIn = {
                navHostController.navigateUp()
            },
            onAction = onAction
        )

        1 -> OtpSignUpScreen(
            state = state,
            onBackPressed = {
                goBack()
            },
            onAction = onAction
        )
    }
}

@Preview
@Composable
fun TermAndConditionScreenPreview() {
    UwangTheme {
        SignUpScreen()
    }
}
