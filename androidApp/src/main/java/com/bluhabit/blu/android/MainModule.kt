/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android

import android.content.Context
import com.bluhabit.blu.android.common.event.EventPublisher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(
    SingletonComponent::class
)
object MainModule {

    @Provides
    fun provideObserver(
        @ApplicationContext appContext: Context
    ): EventPublisher = EventPublisher()
}