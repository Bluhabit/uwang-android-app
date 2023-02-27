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
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bluehabit.budgetku.android.base.BaseMainApp

@Composable
fun DashboardBottomNavigation() {
    BottomNavigation() {
        BottomNavigationItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(imageVector = Icons.Outlined.Home, contentDescription = "")
            }
        )
        BottomNavigationItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(imageVector = Icons.Outlined.Person, contentDescription = "")
            }
        )
    }
}

@Preview
@Composable
fun PreviewDashboardBottomNavigation() {
    BaseMainApp(
        bottomBar = {
            DashboardBottomNavigation()
        }
    )
}