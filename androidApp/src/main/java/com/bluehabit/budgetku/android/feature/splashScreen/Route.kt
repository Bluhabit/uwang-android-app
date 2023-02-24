package com.bluehabit.budgetku.android.feature.splashScreen

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.EventListener
import com.bluehabit.budgetku.android.base.extensions.pushRoute
import com.bluehabit.budgetku.android.feature.signIn.SignIn

object Splash {
    const val routeName = "Splash"
}

fun NavGraphBuilder.routeSplash(
    state: ApplicationState,
    event: EventListener
) {
    composable(Splash.routeName) {
        val viewModel = hiltViewModel<SplashViewModel>()
        LaunchedEffect(key1 = viewModel, block = {
            viewModel.checkIfUserLoggedIn {
                if(!it){
                    state.pushRoute(SignIn.routeName)
                }
            }
        })
        ScreenSplash()
    }
}