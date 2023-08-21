/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.dashboard.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.contract.UIContract
import app.trian.mvi.ui.internal.rememberUIController
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.theme.GaweanTheme

@Navigation(
    route = Routes.Dashboard.routeName,
    viewModel = DashboardViewModel::class
)
@Composable
fun DashboardScreen(
    uiContract: UIContract<DashboardState, DashboardAction>,
    modifier: Modifier = Modifier
) = UIWrapper(
    uiContract = uiContract
) {
    UseEffect<DashboardEffect>(commit = {
        copy(effect = DashboardEffect.Nothing)
    }, onEffect = {
        when (this) {
            DashboardEffect.CloseApp -> {
                navigator.navigateUp()
            }

            DashboardEffect.Nothing -> Unit
        }
    })
    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "Todo")
            }
        },
        bottomBar = {

        }
    ) {
        Column(
            modifier = modifier.padding(it)
        ) {
            ButtonPrimary(
                text = "Keluar",
                onClick = {
                    dispatch(DashboardAction.SignOut)
                }
            )
        }
    }
}


@Preview
@Composable
fun PreviewDashboardScreen() {
    GaweanTheme() {
        DashboardScreen(
            UIContract(
                controller = rememberUIController(),
                state = DashboardState()
            )
        )
    }

}