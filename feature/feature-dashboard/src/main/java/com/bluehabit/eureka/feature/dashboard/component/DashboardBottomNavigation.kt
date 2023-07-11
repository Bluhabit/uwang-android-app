/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.dashboard.component

import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.theme.Grey500

sealed class DashboardBottomNavigationMenu(
    val route: String = "",
    val name: Int = R.string.label_nav_report_dashboard,
    val iconActive: Int = R.drawable.nav_home_active,
    val iconInactive: Int = R.drawable.nav_home_inactive
) {
    object MenuHome : DashboardBottomNavigationMenu(
        route = Routes.Home.routeName,
        name = R.string.label_nav_home_dashboard,
        iconActive = R.drawable.nav_home_active,
        iconInactive = R.drawable.nav_home_inactive
    )

    object MenuCommunity : DashboardBottomNavigationMenu(
        route = Routes.Community.routeName,
        name = R.string.label_nav_community_dashboard,
        iconActive = R.drawable.nav_community_active,
        iconInactive = R.drawable.nav_community_inactive
    )

    object MenuMiddle : DashboardBottomNavigationMenu(
        route = "MIDDLE",
        name = R.string.label_nav_community_dashboard,
        iconActive = R.drawable.nav_community_active,
        iconInactive = R.drawable.nav_community_inactive
    )

    object MenuBudget : DashboardBottomNavigationMenu(
        route = Routes.Budget.routeName,
        name = R.string.label_nav_budget_dashboard,
        iconActive = R.drawable.nav_budget_active,
        iconInactive = R.drawable.nav_budget_inactive
    )

    object MenuReport : DashboardBottomNavigationMenu(
        route = Routes.Report.routeName,
        name = R.string.label_nav_report_dashboard,
        iconActive = R.drawable.nav_report_active,
        iconInactive = R.drawable.nav_report_inactive
    )
}

var menus = listOf(
    DashboardBottomNavigationMenu.MenuHome,
    DashboardBottomNavigationMenu.MenuCommunity,
    DashboardBottomNavigationMenu.MenuMiddle,
    DashboardBottomNavigationMenu.MenuBudget,
    DashboardBottomNavigationMenu.MenuReport
)


@Composable
fun DashboardBottomNavigation(
    currentRoute: String,
    onRefresh: (DashboardBottomNavigationMenu) -> Unit = {},
    onClick: (DashboardBottomNavigationMenu) -> Unit = {}
) {
    val ctx = LocalContext.current
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        cutoutShape = null,
        modifier = Modifier.height(65.dp)
    ) {

        menus.forEach {
            if (it.route == "MIDDLE") {
               // Spacer(modifier = Modifier.width(40.dp.from(ctx)))
            } else {
                BottomNavigationItem(
                    selected = currentRoute == it.route,
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = Grey500,
                    onClick = {
                        if (currentRoute != it.route) onClick(it)
                        else onRefresh(it)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(
                                id = if (currentRoute == it.route) it.iconActive
                                else it.iconInactive
                            ),
                            contentDescription = stringResource(id = it.name)
                        )
                    },
                    label = {
                        Text(text = stringResource(id = it.name))
                    }
                )
            }
        }
    }

}

@Preview
@Composable
fun PreviewDashboardBottomNavigation() {
    BaseMainApp {
        DashboardBottomNavigation(
            currentRoute = "",
            onClick = {}
        )
    }

}