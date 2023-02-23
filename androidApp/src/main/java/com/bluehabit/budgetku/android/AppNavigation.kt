package com.bluehabit.budgetku.android

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.bluehabit.budgetku.android.base.EventListener
import com.bluehabit.budgetku.android.feature.splashScreen.Splash
import com.bluehabit.budgetku.android.feature.splashScreen.routeSplash
import com.bluehabit.budgetku.android.feature.user.routeUser

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
        routeUser(
            state=applicationState,
            event = eventListener
        )
    }
}