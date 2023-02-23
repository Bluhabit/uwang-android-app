package com.bluehabit.budgetku

import android.content.Context
import com.bluehabit.budgetku.user.UserSDK
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson
import okhttp3.Interceptor

fun createUserSDK(context: Context, interceptor: Interceptor): UserSDK = UserSDK(
    driverFactory = DriverFactory(context),
    httpClient = HttpClient(
        OkHttp
    ) {
        install(ContentNegotiation) {
            gson {
                setLenient()
                setPrettyPrinting()
            }
        }
        engine {
            config { followRedirects(true) }
            addInterceptor(interceptor)
        }
    }
)