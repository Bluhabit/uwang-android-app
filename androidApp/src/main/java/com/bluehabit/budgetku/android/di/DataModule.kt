package com.bluehabit.budgetku.android.di

import android.content.Context
import app.trian.learnkmm.NoteSDK
import app.trian.learnkmm.createAndroidSDK
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module()
@InstallIn(
    SingletonComponent::class
)
object DataModule {
    @Provides
    fun provideDataSDK(
        @ApplicationContext appContext: Context
    ): NoteSDK = createAndroidSDK(
        context = appContext,
        interceptor = ChuckerInterceptor.Builder(
            appContext
        )
            .collector(
                ChuckerCollector(
                    context = appContext,
                    showNotification = true,
                    retentionPeriod = RetentionManager.Period.ONE_HOUR
                )
            )
            .build()
    )
}