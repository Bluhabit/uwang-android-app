/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.splashScreen

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.base.extensions.navigateAndReplaceAll
import com.bluehabit.budgetku.android.base.extensions.navigateSingleTop
import com.bluehabit.budgetku.android.feature.dashboard.home.Home

object Splash {
    const val routeName = "Splash"
}

fun NavGraphBuilder.routeSplash(
    state: ApplicationState,
) {
    composable(Splash.routeName) {
        UIWrapper<String, SplashViewModel>(appState = state) {
            LaunchedEffect(key1 = this, block = {
                checkIfUserLoggedIn()
            })
            ScreenSplash()
        }

    }
}