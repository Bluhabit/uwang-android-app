package app.trian.learnkmm

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation

class CreateIOSSDK() {
    fun createNoteSDK() = NoteSDK(
        driverFactory = DriverFactory(),
        httpClient = HttpClient() {
            install(ContentNegotiation) {

            }
        }
    )
}
