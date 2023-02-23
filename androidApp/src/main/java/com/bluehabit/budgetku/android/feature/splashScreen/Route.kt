package com.bluehabit.budgetku.android.feature.splashScreen

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.EventListener

object Splash {
    const val routeName = "Splash"
}

fun NavGraphBuilder.routeSplash(
    state: ApplicationState,
    event: EventListener
) {
    composable(Splash.routeName) {
        val viewModel = hiltViewModel<SplashViewModel>()

        ScreenSplash()
    }
}