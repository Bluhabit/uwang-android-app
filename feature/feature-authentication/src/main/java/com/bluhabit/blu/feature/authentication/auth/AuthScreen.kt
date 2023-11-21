/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.feature.authentication.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.contract.UIContract
import app.trian.mvi.ui.internal.rememberUIController
import com.bluhabit.core.ui.routes.Routes
import com.bluhabit.core.ui.theme.GaweanTheme

@Navigation(
    route = Routes.Auth.routeName,
    viewModel = AuthViewModel::class
)
@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    uiContract: UIContract<AuthState, AuthAction>,
) = UIWrapper(uiContract = uiContract) {

}

@Preview(backgroundColor = 0xFFFCFAFF)
@Composable
fun PreviewSignInScreen() {
    var state by remember {
        mutableStateOf(AuthState())
    }
    GaweanTheme {
        AuthScreen(
            uiContract = UIContract(
                controller = rememberUIController(),
                state = state,
                mutation = {
                    state = it
                }
            )
        )
    }
}