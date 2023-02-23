package com.bluehabit.budgetku.note

import com.bluehabit.budgetku.DriverFactory
import com.bluehabit.budgetku.createDatabase
import com.bluehabit.budgetku.entity.NoteModel
import com.bluehabit.budgetku.sharedPref.KMMPreference
import io.ktor.client.HttpClient

class NoteSDK(
    private val driverFactory: DriverFactory,
    private val httpClient: HttpClient,
    private val kmmPreference: KMMPreference
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