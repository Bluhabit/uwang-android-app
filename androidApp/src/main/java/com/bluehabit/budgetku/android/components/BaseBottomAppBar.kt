/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */


package com.bluehabit.budgetku.android.components

import androidx.compose.runtime.Composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.listener.BottomAppBarType
import com.bluehabit.budgetku.android.feature.dashboard.components.DashboardBottomNavigation

@Composable
fun BaseBottomAppBar(
    state: ApplicationState
) {
    with(state) {
        when (bottomAppBarType) {
            BottomAppBarType.HIDE -> Unit
            BottomAppBarType.BASIC -> TODO()
            BottomAppBarType.DASHBOARD -> {
                DashboardBottomNavigation(
                    appState = state
                )
            }
        }
    }
}