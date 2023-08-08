/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.dashboard.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Checkbox
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.contract.UIContract
import app.trian.mvi.ui.internal.rememberUIController
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.theme.BudgetKuTheme

@Navigation(
    route = Routes.Home.routeName,
    viewModel = DashboardViewModel::class
)
@Composable
fun DashboardScreen(
    uiContract: UIContract<DashboardState, DashboardAction>,
    modifier: Modifier = Modifier
) = UIWrapper(
    uiContract = uiContract
) {
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

        }
    }
}


@Preview
@Composable
fun PreviewDashboardScreen() {
    BudgetKuTheme() {
        DashboardScreen(
            UIContract(
                controller = rememberUIController(),
                state = DashboardState()
            )
        )
    }

}