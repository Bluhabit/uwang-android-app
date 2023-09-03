/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.navigationbar

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray500
import com.bluehabit.core.ui.theme.Primary600


open class DashboardBottomNavigationMenu(
    val screen: Int = 0,
    val name: Int = R.string.text_label_nav_report_dashboard,
    val iconActive: Int = R.drawable.icon_item_bottom_navigation_user_active,
    val iconInactive: Int = R.drawable.icon_item_bottom_navigation_user_active,
) {
    object MenuHome : DashboardBottomNavigationMenu(
        screen = 0,
        name = R.string.text_label_nav_home_dashboard,
        iconActive = R.drawable.icon_item_bottom_navigation_home_active,
        iconInactive = R.drawable.icon_item_bottom_navigation_home_inactive
    )

    object MenuTask : DashboardBottomNavigationMenu(
        screen = 1,
        name = R.string.text_label_nav_task_dashboard,
        iconActive = R.drawable.icon_item_bottom_task_user_active,
        iconInactive = R.drawable.icon_item_bottom_task_user_inactive
    )

    object MenuProject : DashboardBottomNavigationMenu(
        screen = 2,
        name = R.string.text_label_nav_project_dashboard,
        iconActive = R.drawable.icon_item_bottom_navigation_group_active,
        iconInactive = R.drawable.icon_item_bottom_navigation_group_inactive
    )

    object MenuProfile : DashboardBottomNavigationMenu(
        screen = 3,
        name = R.string.text_label_nav_profile_dashboard,
        iconActive = R.drawable.user_active,
        iconInactive = R.drawable.user_inactive
    )
}

var menus = listOf(
    DashboardBottomNavigationMenu.MenuHome,
    DashboardBottomNavigationMenu.MenuTask,
    DashboardBottomNavigationMenu.MenuProject,
    DashboardBottomNavigationMenu.MenuProfile
)


@Composable
fun DashboardBottomNavigation(
    currentRoute: Int,
    onRefresh: (DashboardBottomNavigationMenu) -> Unit = {},
    onClick: (DashboardBottomNavigationMenu) -> Unit = {}
) {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        cutoutShape = null,
        modifier = Modifier.height(65.dp)
    ) {

        menus.forEach {
            BottomNavigationItem(
                selected = currentRoute == it.screen,
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Gray500,
                onClick = {
                    if (currentRoute == it.screen) onRefresh(it)
                    else onClick(it)
                },
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (currentRoute == it.screen) it.iconActive
                            else it.iconInactive
                        ),
                        contentDescription = stringResource(id = it.name),
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(text = stringResource(id = it.name))
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewDashboardBottomNavigation() {
    GaweanTheme() {
        Scaffold(
            bottomBar = {
                DashboardBottomNavigation(
                    currentRoute = 0,
                    onClick = {}
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_item_bottom_navigation_user_active),
                            contentDescription = null,
                            modifier = Modifier.size(37.dp)
                        )
                    },
                    backgroundColor = Primary600,
                    contentColor = Color.White
                )
            },
            isFloatingActionButtonDocked = true,
            floatingActionButtonPosition = FabPosition.Center
        ) {
            it

        }
    }
}