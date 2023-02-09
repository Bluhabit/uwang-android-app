package com.bluehabit.budgetku

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.bluehabit.budgetku.entity.NoteModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class NoteSDK(
    private val driverFactory: DriverFactory,
    private val httpClient: HttpClient
) {
    private val api = NoteApi(httpClient)
    private val db = createDatabase(driverFactory)

    @Throws(
        Exception::class
    )
    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Pair<Boolean, String> {

        val res =
            api.signInWithEmail(email, password)
        return Pair(true, res)
    }

    @Throws(
        Exception::class
    )
    suspend fun getListAllNote(): List<NoteModel> {
        return db.noteQueries.selectAll()
            .executeAsList().map {
                NoteModel(
                    noteId = it.noteId,
                    noteName = it.noteName,
                    noteDescription = it.noteDescription
                )
            }
    }



    @Throws(
        Exception::class
    )
    suspend fun insertNewNote(data: NoteModel): Triple<Boolean, String, NoteModel> {
        db.noteQueries.insertNote(
            noteId = data.noteId,
            noteName = data.noteName,
            noteDescription = data.noteDescription
        )

        return Triple(
            true,
            "Success",
            data
        )

    }

    @Throws(Exception::class)
    suspend fun deleteNoteById(noteId: String): Pair<Boolean, String> {
        db.noteQueries.deleteNoteById(noteId)

        return Pair(true, "Success")
    }

}