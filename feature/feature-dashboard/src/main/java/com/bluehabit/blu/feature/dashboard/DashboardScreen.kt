/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.blu.feature.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.contract.UIContract
import app.trian.mvi.ui.internal.rememberUIController
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

    })


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