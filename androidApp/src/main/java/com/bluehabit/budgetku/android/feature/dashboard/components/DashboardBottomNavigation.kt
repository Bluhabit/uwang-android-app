/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.extensions.navigationItemClick
import com.bluehabit.budgetku.android.base.extensions.navigateSingleTop
import com.bluehabit.budgetku.android.feature.dashboard.home.Home
import com.bluehabit.budgetku.android.feature.dashboard.profile.Profile
import com.bluehabit.budgetku.android.rememberApplicationState

sealed class DashboardBottomNavigationMenu(
    val route: String = "",
    val name: String = "",
    val icon: ImageVector = Icons.Outlined.Home
) {
    object MenuHome : DashboardBottomNavigationMenu(
        route = Home.routeName,
        name = "Home",
        icon = Icons.Outlined.Home
    )

    object MenuProfile : DashboardBottomNavigationMenu(
        route = Profile.routeName,
        name = "Profile",
        icon = Icons.Outlined.Person
    )
}

var menus = listOf(
    DashboardBottomNavigationMenu.MenuHome,
    DashboardBottomNavigationMenu.MenuProfile
)

@Composable
fun DashboardBottomNavigation(
    appState: ApplicationState
) {
    BottomNavigation() {
        menus.forEach {
            BottomNavigationItem(
                selected = appState.currentRoute == it.route,
                selectedContentColor = MaterialTheme.colorScheme.onBackground,
                unselectedContentColor = MaterialTheme.colorScheme.onPrimary,
                onClick = {
                    with(appState) {
                        navigationItemClick(it.route)
                        navigateSingleTop(it.route)
                    }
                },
                icon = {
                    Icon(imageVector = it.icon, contentDescription = it.name)
                }
            )
        }

    }
}

@Preview
@Composable
fun PreviewDashboardBottomNavigation() {
    BaseMainApp(
        bottomBar = {
            DashboardBottomNavigation(
                appState = rememberApplicationState()
            )
        }
    )
}