/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.community

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.base.extensions.bottomNavigationListener
import com.bluehabit.budgetku.android.base.extensions.navigateSingleTop
import com.bluehabit.budgetku.android.base.listener.BottomNavigationListener
import com.bluehabit.budgetku.android.components.DashboardBottomNavigationMenu

object Community {
    const val routeName = "Community"
}

fun NavGraphBuilder.routeCommunity(
    state: ApplicationState,
) {
    composable(Community.routeName) {
        ScreenCommunity(appState = state)
    }
}

@Composable
internal fun ScreenCommunity(
    appState: ApplicationState,
) = UIWrapper<CommunityViewModel>(appState = appState) {
    with(appState) {
        bottomNavigationListener(object : BottomNavigationListener {
            override fun onRefresh(item: DashboardBottomNavigationMenu) {
                // remove empty
            }

            override fun onNavigate(item: DashboardBottomNavigationMenu) {
                navigateSingleTop(item.route)
            }

            override fun onFab() {
                //remove empty
            }

        })
    }
    Column {

    }
}

@Preview
@Composable
fun PreviewScreenCommunity() {
    BaseMainApp {
        ScreenCommunity(it)
    }
}