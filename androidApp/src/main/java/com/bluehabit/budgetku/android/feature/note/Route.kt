package com.bluehabit.budgetku.android.feature.note

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.EventListener

object Note {
    const val routeName = "Note"
}

fun NavGraphBuilder.routeNote(
    state: ApplicationState,
    event: EventListener
) {
    composable(Note.routeName) {
        val viewModel =
            hiltViewModel<NoteViewModel>()

        val dataNotes by viewModel.dataNotes.collectAsState()

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