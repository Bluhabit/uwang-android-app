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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.trian.mvi.Argument
import app.trian.mvi.DeepLink
import app.trian.mvi.NavType
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.contract.UIContract
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.eureka.data.authentication.AuthConstant.AUTH_SCREEN_CREATE_PASSWORD
import com.bluehabit.eureka.data.authentication.AuthConstant.AUTH_SCREEN_INSTRUCTION_RESET_PASSWORD
import com.bluehabit.eureka.data.authentication.AuthConstant.AUTH_SCREEN_LINK_CONFIRMATION
import com.bluehabit.eureka.data.authentication.AuthConstant.AUTH_SCREEN_RESET_PASSWORD
import com.bluehabit.eureka.data.authentication.AuthConstant.AUTH_SCREEN_RESET_SUCCESS

@Navigation(
    route = Routes.ResetPassword.routeName,
    viewModel = ResetPasswordViewModel::class
)
@Argument(
    name = Routes.ResetPassword.argDeeplink,
    navType = NavType.String
)
@DeepLink(
    uri = Routes.ResetPassword.deepLink
)
@Composable
fun ResetPasswordScreen(
    uiContract: UIContract<ResetPasswordState, ResetPasswordAction>
) = UIWrapper(uiContract = uiContract) {
    Scaffold(
        bottomBar = {}
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            when (state.currentScreen) {
                AUTH_SCREEN_RESET_PASSWORD -> Unit
                AUTH_SCREEN_INSTRUCTION_RESET_PASSWORD -> Unit
                AUTH_SCREEN_LINK_CONFIRMATION -> Unit
                AUTH_SCREEN_CREATE_PASSWORD -> Unit
                AUTH_SCREEN_RESET_SUCCESS -> Unit
            }
        }
    }
}