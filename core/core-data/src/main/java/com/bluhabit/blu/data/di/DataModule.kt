/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.data.di

import android.content.Context
import android.content.SharedPreferences
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.bluehabit.uwang.db.Database
import com.bluhabit.blu.data.BuildConfig
import com.bluhabit.blu.data.common.ResourceProvider
import com.bluhabit.blu.data.persistence.SharedPref
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.gson.gson
import io.ktor.serialization.kotlinx.json.json
import java.util.Locale
import okhttp3.logging.HttpLoggingInterceptor


@Module
@InstallIn(
    SingletonComponent::class
)
object DataModule {


    @Provides
    fun provideResource(
        @ApplicationContext appContext: Context,
    ) : ResourceProvider = ResourceProvider(appContext)

    @Provides
    fun provideSharedPref(
        @ApplicationContext appContext: Context
    ): SharedPreferences = appContext.getSharedPreferences(
        BuildConfig.SHARED_PREFERENCES,
        Context.MODE_PRIVATE
    )

    @Provides
    fun provideLocalSession(
        sharedPreferences: SharedPreferences
    ): SharedPref = SharedPref(
        sharedPreferences
    )

    @Provides
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): SqlDriver = AndroidSqliteDriver(
        Database.Schema,
        appContext,
        BuildConfig.DATABASE
    )

    @Provides
    fun provideHttpClient(
        @ApplicationContext appContext: Context,
        sharedPref: SharedPref
    ): HttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val chucker = ChuckerInterceptor
            .Builder(appContext)
            .collector(
                ChuckerCollector(
                    context = appContext,
                    showNotification = true,
                    retentionPeriod = RetentionManager.Period.ONE_HOUR
                )
            )
            .maxContentLength(250_000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()
        val okHttpEngine = OkHttp.create {
            addInterceptor(chucker)
            addInterceptor(logging)
        }
        return HttpClient(okHttpEngine) {
            expectSuccess = true
            install(HttpTimeout) {
                socketTimeoutMillis = 180_000
            }
            install(Resources)
            defaultRequest {
                url(BuildConfig.BASE_URL)
                val token = sharedPref.getToken()
                header("Authorization", "Bearer ".plus(token))
                header("Accept-Language", Locale.forLanguageTag("ID").language)
                contentType(ContentType.Application.Json)
            }
            install(ContentNegotiation) {
                gson {
                    setLenient()
                    setPrettyPrinting()
                }
            }

        }
    }
}


