/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.home

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.extensions.navigateSingleTop
import com.bluehabit.budgetku.android.feature.dashboard.components.DashboardBottomNavigation

object Home {
    const val routeName = "Home"
}

fun NavGraphBuilder.routeHome(
    state: ApplicationState,
) {
    composable(Home.routeName) {
        val viewModel = hiltViewModel<HomeViewModel>()

        LaunchedEffect(key1 = state, block = {
            with(state){
                setupTopAppBar {
                    TopAppBar {
                        Text(text = "INi Top")
                    }
                }
                setupBottomAppBar{
                    DashboardBottomNavigation(currentRoute = currentRoute){
                        state.navigateSingleTop(it.route)
                    }
                }
            }
        })

        ScreenHome()
    }
}