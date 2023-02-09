package com.bluehabit.budgetku.android

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.bluehabit.budgetku.android.feature.note.Note
import com.bluehabit.budgetku.android.feature.note.routeNote

@Composable
fun AppNavigation(
    applicationState: ApplicationState
) {
    NavHost(
        navController = applicationState.router,
        startDestination = Note.routeName
    ) {
        routeNote(applicationState.router)
    }
}