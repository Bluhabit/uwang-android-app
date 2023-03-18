/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.base.extensions.navigateSingleTop
import com.bluehabit.budgetku.android.feature.dashboard.components.DashboardBottomNavigation
import com.bluehabit.budgetku.android.rememberApplicationState

object Profile {
    const val routeName = "Profile"
}

fun NavGraphBuilder.routeProfile(
    state: ApplicationState
) {
    composable(Profile.routeName) {
        ScreenProfile(appState = state)
    }
}

@Composable
internal fun ScreenProfile(
    appState: ApplicationState
) {
    UIWrapper<ProfileState, ProfileEvent, ProfileViewModel>(
        appState = appState,
        parent = "Dashboard"
    ) { (name) ->
        with(appState) {
            setupTopAppBar {
                TopAppBar {
                    Text(text = name)
                }
            }
            setupBottomAppBar {
                DashboardBottomNavigation(currentRoute = currentRoute) {
                    navigateSingleTop(it.route)
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Button(onClick = {
                sendEvent(ProfileEvent.SetName(Math.random().toString()))
            }) {
                Text(text = "Klik Coba")
            }
        }
    }

}

@Preview
@Composable
fun PreviewScreenProfile() {
    BaseMainApp {
        ScreenProfile(rememberApplicationState())
    }

}