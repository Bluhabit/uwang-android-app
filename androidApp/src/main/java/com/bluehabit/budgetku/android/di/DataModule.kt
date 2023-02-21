package com.bluehabit.budgetku.android.di

import android.content.Context
import com.bluehabit.budgetku.createAndroidSDK
import com.bluehabit.budgetku.createUserSDK
import com.bluehabit.budgetku.note.NoteSDK
import com.bluehabit.budgetku.user.UserSDK
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

    @Provides
    fun providerUserSDK(
        @ApplicationContext appContext: Context
    ): UserSDK = createUserSDK(
        context = appContext,
        interceptor = ChuckerInterceptor.Builder(
            appContext
        ).collector(
            ChuckerCollector(
                context = appContext,
                showNotification = true,
                retentionPeriod = RetentionManager.Period.ONE_HOUR
            )
        ).build()
    )
}