package com.bluehabit.budgetku

import android.content.Context
import com.bluehabit.budgetku.note.NoteSDK
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson
import okhttp3.Interceptor


fun createAndroidSDK(context:Context, interceptor: Interceptor): NoteSDK {
    return NoteSDK(
        driverFactory = DriverFactory(context),
        httpClient = HttpClient(
            OkHttp
        ) {
            install(ContentNegotiation){
                gson {
                    setLenient()
                }
            }
            engine {
                config { followRedirects(true) }
                addInterceptor(interceptor)
            }
        }
    )
}


