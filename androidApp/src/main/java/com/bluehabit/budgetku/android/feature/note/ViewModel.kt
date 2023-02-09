package com.bluehabit.budgetku.android.feature.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluehabit.budgetku.NoteSDK
import com.bluehabit.budgetku.entity.NoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteSDK: NoteSDK
) : ViewModel() {

    private val _dataNotes =
        MutableStateFlow<List<NoteModel>>(listOf())
    val dataNotes = _dataNotes.asStateFlow()

    init {
        getAllNotes()
    }


    fun insertNewNote(
        noteName:String,
        result: (Boolean, String) -> Unit
    ) = with(viewModelScope) {
        launch {
            noteSDK.insertNewNote(
                NoteModel(
                    noteId = UUID.randomUUID().toString(),
                    noteName = noteName,
                    noteDescription = "Ini Description dari $noteName"
                )
            )
            getAllNotes()
        }
    }

    fun deleteNoteById(noteId: String) = with(viewModelScope) {
        launch {
            noteSDK.deleteNoteById(noteId)
            getAllNotes()
        }
    }

    fun getAllNotes() = with(viewModelScope) {
        launch {
            val data = noteSDK.getListAllNote()
            _dataNotes.emit(data)
        }
    }
}