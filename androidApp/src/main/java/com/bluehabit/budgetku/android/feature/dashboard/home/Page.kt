/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.base.extensions.navigateSingleTop
import com.bluehabit.budgetku.android.components.DashboardBottomNavigation
import com.bluehabit.budgetku.android.rememberApplicationState

object Home {
    const val routeName = "Home"
}

fun NavGraphBuilder.routeHome(
    state: ApplicationState
) {
    composable(Home.routeName) {
        ScreenHome(appState = state)

    }
}

@Composable
internal fun ScreenHome(
    appState: ApplicationState
) = UIWrapper<HomeViewModel>(
    appState = appState
) {
    val state by uiState.collectAsState(initial = HomeState())


    with(appState) {
        setupTopAppBar {
            TopAppBar {
                Text(text = "title ${state.name}")
            }
        }
        setupBottomAppBar {
            DashboardBottomNavigation(currentRoute = currentRoute, onRefresh = {}) {
                appState.navigateSingleTop(it.route)
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = {
            dispatch(HomeEvent.SetName("Hayolo"))
        }) {
            Text(text = "INI")
        }
    }

}


@Preview
@Composable
fun PreviewScreenHome() {
    BaseMainApp {
        ScreenHome(rememberApplicationState())
    }

}