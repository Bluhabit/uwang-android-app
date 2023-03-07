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
import com.bluehabit.budgetku.android.base.extensions.navigateAndReplaceAll
import com.bluehabit.budgetku.android.feature.dashboard.home.Home

object Splash {
    const val routeName = "Splash"
}

fun NavGraphBuilder.routeSplash(
    state: ApplicationState,
) {
    composable(Splash.routeName) {
        val viewModel = hiltViewModel<SplashViewModel>()
        LaunchedEffect(key1 = viewModel, block = {
            state.navigateAndReplaceAll(Home.routeName)
//            viewModel.checkIfUserLoggedIn {
//
//                with(state) {
//                    if (it) {
//                        navigateAndReplaceAll(Home.routeName)
//                    } else {
//                        navigateAndReplaceAll(SignIn.routeName)
//                    }
//                }
//            }
        })

        ScreenSplash()
    }
}