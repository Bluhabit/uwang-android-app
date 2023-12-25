/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signin

import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bluhabit.blu.android.presentation.authentication.signin.screen.InputSignInScreen
import com.bluhabit.blu.android.presentation.authentication.signin.screen.OtpSignInScreen
import com.bluhabit.blu.data.common.Response
import com.bluhabit.blu.data.contract.GoogleAuthContract
import com.bluhabit.core.ui.theme.UwangTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun SignInScreen(
    navHostController: NavHostController = rememberNavController(),
    stateFlow: Flow<SignInState> = flowOf(),
    effectFlow: Flow<SignInEffect> = flowOf(),
    onAction: (SignInAction) -> Unit = {},
) {
    val state by stateFlow.collectAsStateWithLifecycle(initialValue = SignInState())
    val effect by effectFlow.collectAsStateWithLifecycle(initialValue = SignInEffect.None)

    LaunchedEffect(key1 = effect, block = {
        when (effect) {
            SignInEffect.None -> Unit
            SignInEffect.NavigateToCompleteProfile -> {
                navHostController.navigate("complete_profile")
            }

            SignInEffect.NavigateToMain -> Unit
        }
    })

    val googleAuthLauncher = rememberLauncherForActivityResult(contract = GoogleAuthContract(), onResult = {
        when (it) {
            is Response.Error -> Unit
            is Response.Result -> {
                onAction(SignInAction.OnSignInGoogle(it.data))
            }
        }
    })

    BackHandler {
        if (state.currentScreen == 1) {
            onAction(SignInAction.OnScreenChange(0))
        } else {
            navHostController.navigateUp()
        }
    }

    when (state.currentScreen) {
        0 -> {
            InputSignInScreen(
                state = state,
                onSignInGoogle = { googleAuthLauncher.launch(1) },
                onSignUp = {
                    navHostController.navigate("sign_up")
                },
                action = onAction
            )
        }

        1 -> OtpSignInScreen(
            state = state,
            onBackPressed = {
                onAction(SignInAction.OnScreenChange(0))
            }
        )
    }

}

@Preview
@Composable
fun SignInScreenPreview() {
    UwangTheme {
        SignInScreen()
    }
}
