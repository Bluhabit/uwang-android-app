package com.bluehabit.budgetku.android.di

import android.content.Context
import com.bluehabit.budgetku.AndroidSetupSDK
import com.bluehabit.budgetku.BuildConfig
import com.bluehabit.budgetku.createAuthSDK
import com.bluehabit.budgetku.createDataConfiguration
import com.bluehabit.budgetku.createUserSDK
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(
    SingletonComponent::class
)
object DataModule {
    @Provides
    fun provideChuckerInterceptor(
        @ApplicationContext appContext: Context
    ): ChuckerInterceptor = ChuckerInterceptor.Builder(
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

    @Provides
    fun provideSDKConfig(
        @ApplicationContext appContext: Context,
        chuckerInterceptor: ChuckerInterceptor
    ) = createDataConfiguration(
        appContext,
        chuckerInterceptor,
        BuildConfig.BASE_URL
    )

    @Provides
    fun providerAuthSDK(
        sdk: AndroidSetupSDK
    ) = createAuthSDK(
        sdk
    )

    @Provides
    fun provideUSerSDK(
        sdk: AndroidSetupSDK
    ) = createUserSDK(
        sdk
    )
}