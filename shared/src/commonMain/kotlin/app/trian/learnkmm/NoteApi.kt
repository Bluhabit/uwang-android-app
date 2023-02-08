package app.trian.learnkmm

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class NoteApi(
     private val client:HttpClient
) {
     private val BASE_URL = "https://ktor.io"
     suspend fun signInWithEmail(email:String,password:String) =
          client.get("${BASE_URL}/docs/welcome.html").bodyAsText()
}