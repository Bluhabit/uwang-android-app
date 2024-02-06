/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.common.event

class EventObserverImpl<T>(
    val on:(T)->Unit
):EventObserver<T> {
    override fun onEvent(event: T) {
        on(event)
    }
}