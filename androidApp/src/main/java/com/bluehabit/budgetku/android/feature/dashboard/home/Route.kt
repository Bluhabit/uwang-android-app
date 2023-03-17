/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.home

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.base.extensions.navigateSingleTop
import com.bluehabit.budgetku.android.feature.dashboard.components.DashboardBottomNavigation

object Home {
    const val routeName = "Home"
}

fun NavGraphBuilder.routeHome(
    state: ApplicationState
) {
    composable(Home.routeName) {

        UIWrapper<String, HomeViewModel>(
            appState = state,
            parent = "Dashboard"
        ) {
            with(state) {
                setupTopAppBar {
                    TopAppBar {
                        Text(text = it.orEmpty())
                    }
                }
                setupBottomAppBar {
                    DashboardBottomNavigation(currentRoute = currentRoute) {
                        state.navigateSingleTop(it.route)
                    }
                }
            }

            ScreenHome(
                onClick = {
                    setName("Hayolo")
                }
            )
        }


    }
}