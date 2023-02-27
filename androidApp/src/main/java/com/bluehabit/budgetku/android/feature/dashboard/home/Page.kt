/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.home

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.EventListener
import com.bluehabit.budgetku.android.feature.dashboard.Dashboard

object Home {
    const val routeName = "Home"
}

fun NavGraphBuilder.routeHome(
    state: ApplicationState,
    event: EventListener
) {
    composable(Home.routeName) {
        val viewModel = hiltViewModel<HomeViewModel>()
        LaunchedEffect(key1 = state, block = {
            state.changeBottomBar(Dashboard.BottomNavigationType)
        })

        ScreenHome()
    }
}