/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.di

import android.content.Context
import android.content.SharedPreferences
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.bluehabit.budgetku.BuildConfig
import com.bluehabit.budgetku.data.local.SharedPref
import com.bluehabit.budgetku.db.Database
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
import java.util.Locale


@Module
@InstallIn(
    SingletonComponent::class
)
object DataModule {


    @Provides
    fun provideSharedPref(
        @ApplicationContext appContext: Context
    ): SharedPreferences = appContext.getSharedPreferences(
        "fdasa-34fdsf-465ds",
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
        "bluehabit-budgetku.db"
    )

    @Provides
    fun provideHttpClient(
        @ApplicationContext appContext: Context,
        sharedPref: SharedPref
    ): HttpClient  {
        val chucker = ChuckerInterceptor.Builder(
            appContext
        )
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
        }
        return HttpClient(okHttpEngine) {
            expectSuccess = true
            install(HttpTimeout) {
                socketTimeoutMillis = 180_000
            }
            install(Resources)
            defaultRequest {
                url(BuildConfig.BASE_URL)
                val locale = sharedPref.getLanguage()
                header("Accept-Language", locale.ifEmpty { Locale.ENGLISH.language })
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

