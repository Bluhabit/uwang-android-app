/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.bluehabit.budgetku.android.base.EventListener
import com.bluehabit.budgetku.android.feature.splashScreen.Splash
import com.bluehabit.budgetku.android.feature.splashScreen.routeSplash
import com.bluehabit.budgetku.android.feature.signIn.routeSignIn

@Composable
fun AppNavigation(
    applicationState: ApplicationState,
    eventListener: EventListener
) {
    NavHost(
        navController = applicationState.router,
        startDestination = Splash.routeName
    ) {
        routeSplash(
            state=applicationState,
            event = eventListener
        )
        routeSignIn(
            state=applicationState,
            event = eventListener
        )
    }
}