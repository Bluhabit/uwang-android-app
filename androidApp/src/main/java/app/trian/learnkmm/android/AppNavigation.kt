package app.trian.learnkmm.android

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import app.trian.learnkmm.android.feature.note.Note
import app.trian.learnkmm.android.feature.note.routeNote

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