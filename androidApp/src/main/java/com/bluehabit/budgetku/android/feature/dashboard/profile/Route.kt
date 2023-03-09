/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.profile

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.feature.dashboard.components.DashboardBottomNavigation

object Profile {
    const val routeName = "Profile"
}

fun NavGraphBuilder.routeProfile(
    state: ApplicationState
) {
    composable(Profile.routeName) {
        val viewModel = hiltViewModel<ProfileViewModel>()
        var nama by remember {
            mutableStateOf("HEHE")
        }
        LaunchedEffect(key1 = state, block = {
            with(state){
                setupTopAppBar {
                    TopAppBar {
                        Text(text = "Ini dari $nama")
                    }
                }
                setupBottomAppBar{
                    DashboardBottomNavigation(currentRoute = currentRoute){
                        nama = it.name
                    }
                }
            }
        })

        ScreenProfile()

    }
}