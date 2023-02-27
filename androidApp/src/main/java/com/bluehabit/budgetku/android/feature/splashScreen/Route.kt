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
import com.bluehabit.budgetku.android.feature.signIn.SignIn

object Splash {
    const val routeName = "Splash"
}

fun NavGraphBuilder.routeSplash(
    state: ApplicationState,
) {
    composable(Splash.routeName) {
        val viewModel = hiltViewModel<SplashViewModel>()
        LaunchedEffect(key1 = viewModel, block = {
            viewModel.checkIfUserLoggedIn {
                if(it){
                    state.navigateAndReplaceAll(Home.routeName)
                }else{
                    state.navigateAndReplaceAll(SignIn.routeName)
                }
            }
        })
        ScreenSplash()
    }
}