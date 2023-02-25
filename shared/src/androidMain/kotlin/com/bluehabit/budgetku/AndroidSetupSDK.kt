package com.bluehabit.budgetku

import android.content.Context
import com.bluehabit.budgetku.sdk.auth.AuthSDK
import com.bluehabit.budgetku.sharedPref.KMMContext
import com.bluehabit.budgetku.sharedPref.KMMPreference
import com.bluehabit.budgetku.sdk.user.UserSDK
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.resources.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.gson.gson
import okhttp3.Interceptor

class AndroidSetupSDK(
    private val context: Context,
    private val interceptor: Interceptor,
    private val baseUrl: String
) {
    var kmmPreference: KMMPreference = KMMPreference(context as KMMContext)
    var driver = DriverFactory(context)
    var httpClient: HttpClient = HttpClient(
        OkHttp
    ) {
        install(Resources)
        defaultRequest {
            val locale = kmmPreference.getString("locale")
            header("Accept-Language",locale ?: "en")
            url(baseUrl)
            contentType(ContentType.Application.Json)
        }
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

}

fun createDataConfiguration(
    context: Context,
    interceptor: Interceptor,
    baseUrl: String
): AndroidSetupSDK = AndroidSetupSDK(
    context,
    interceptor,
    baseUrl
)

fun createAuthSDK(
    sdk: AndroidSetupSDK
): AuthSDK = AuthSDK(
    driverFactory = sdk.driver,
    client = sdk.httpClient,
    pref = sdk.kmmPreference
)

fun createUserSDK(
    sdk: AndroidSetupSDK
): UserSDK = UserSDK(
    driverFactory = sdk.driver,
    client = sdk.httpClient,
    pref = sdk.kmmPreference
)