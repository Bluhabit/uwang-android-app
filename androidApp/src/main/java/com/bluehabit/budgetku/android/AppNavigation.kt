package com.bluehabit.budgetku.android

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.bluehabit.budgetku.android.feature.note.routeNote
import com.bluehabit.budgetku.android.feature.user.User
import com.bluehabit.budgetku.android.feature.user.routeUser

@Composable
fun AppNavigation(
    applicationState: ApplicationState
) {
    NavHost(
        navController = applicationState.router,
        startDestination = User.routeName
    ) {
        routeNote(applicationState.router)
        routeUser(applicationState.router)
    }
}