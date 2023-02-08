package app.trian.learnkmm.android.feature.note

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

object Note {
    const val routeName = "Note"
}

fun NavGraphBuilder.routeNote(
    router: NavHostController,
) {
    composable(Note.routeName) {
        val viewModel =
            hiltViewModel<NoteViewModel>()

        val dataNotes by viewModel.dataNotes.collectAsState(
            listOf()
        )

        ScreenNote(
            notes = dataNotes,
            onDelete = { noteId ->

                viewModel.deleteNoteById(noteId)
            },
            onSubmit = { noteName ->
                viewModel.insertNewNote(noteName) { b, s -> }
            }
        )

    }
}