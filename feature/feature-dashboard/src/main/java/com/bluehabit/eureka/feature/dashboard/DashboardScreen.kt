/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.contract.UIContract
import app.trian.mvi.ui.internal.rememberUIController
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.navigationbar.DashboardBottomNavigation
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Primary600
import com.bluehabit.eureka.feature.dashboard.screen.HomeScreen
import com.bluehabit.eureka.feature.dashboard.screen.ListTaskScreen

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

    LaunchedEffect(key1 = Unit, block = {
        dispatch(DashboardAction.GetAllListTask)
    })

    Scaffold(
        bottomBar = {
            DashboardBottomNavigation(
                currentRoute = state.dashboardScreen,
                onRefresh = {},
                onClick = {
                    commit { copy(dashboardScreen = it.screen) }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navigator.navigate(Routes.CreateTask.routeName)
                },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_item_bottom_navigation_user_active),
                        contentDescription = null,
                        modifier = Modifier.size(37.dp)
                    )
                },
                backgroundColor = Primary600,
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 1.dp,
                    hoveredElevation = 0.dp,
                    focusedElevation = 0.dp
                )
            )
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Column(
            modifier = modifier.padding(it)
        ) {
            when (state.dashboardScreen) {
                0 -> HomeScreen(
                    state = state,
                    onNotificationIconClick = {
                        dispatch(DashboardAction.SignOut)
                    },
                    onSearch = {},
                    onTabSelected = { index, tab ->
                        commit { copy(selectedTabIndex = index) }
                        dispatch(tab.action)
                    }
                )

                1 -> ListTaskScreen(
                    state = state,
                    onTabSelected = { index, tab ->
                        commit { copy(selectedTabIndex = index) }
                        dispatch(tab.action)
                    },
                    onClickNotification = {
                        dispatch(DashboardAction.SignOut)
                    }
                )
            }
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