/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.signUp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.contract.UIContract
import app.trian.mvi.ui.internal.rememberUIController
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.eureka.feature.authentication.signUp.screen.OtpScreen

@Navigation(
    route = "",
    viewModel = SignUpViewModel::class
)
@Composable
fun SignUpScreen(
    uiContract: UIContract<SignUpState, SignUpAction>
) = UIWrapper(uiContract = uiContract) {
    when (state.currentScreen) {
        1 -> OtpScreen()
    }
}

@Preview
@Composable
fun PreviewSignUpScreen() {
    GaweanTheme {
        SignUpScreen(
            uiContract = UIContract(
                rememberUIController(),
                SignUpState()
            )
        )
    }
}