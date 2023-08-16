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
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.contract.UIContract

@Navigation(
    route = "ResetPassword",
    viewModel = ResetPasswordViewModel::class
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
            when(state.currentScreen){

            }
        }
    }
}