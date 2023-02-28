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
import com.bluehabit.budgetku.android.base.extensions.addOnAppBarListener
import com.bluehabit.budgetku.android.base.extensions.runSuspend
import com.bluehabit.budgetku.android.base.extensions.showSnackbar
import com.bluehabit.budgetku.android.base.listener.AppBarListenerImpl
import com.bluehabit.budgetku.android.feature.dashboard.Dashboard

object Home {
    const val routeName = "Home"
}

fun NavGraphBuilder.routeHome(
    state: ApplicationState,
) {
    composable(Home.routeName) {
        val viewModel = hiltViewModel<HomeViewModel>()
        LaunchedEffect(key1 = state, block = {
            with(state) {
                changeBottomBar(Dashboard.BottomNavigationType)
                addOnAppBarListener(
                    AppBarListenerImpl(
                        onNavButtonClicked = {},
                        onNavItemClicked = { id, _ ->
                            runSuspend {
                                showSnackbar("Ini $id")
                            }
                        },
                        onFabClicked = { _, _ -> },
                        onActionClicked = { _, _ -> }
                    )
                )
            }
        })

        ScreenHome()
    }
}