/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.blu.feature.authentication.signUp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.trian.mvi.Argument
import app.trian.mvi.NavType
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.contract.UIContract
import app.trian.mvi.ui.internal.rememberUIController
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.theme.GaweanTheme

@Navigation(
    route = Routes.SignUp.routeName,
    viewModel = SignUpViewModel::class
)
@Argument(
    name = Routes.SignUp.currentScreenArg,
    navType = NavType.Integer
)
@Composable
fun SignUpScreen(
    uiContract: UIContract<SignUpState, SignUpAction>
) = UIWrapper(uiContract = uiContract) {

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