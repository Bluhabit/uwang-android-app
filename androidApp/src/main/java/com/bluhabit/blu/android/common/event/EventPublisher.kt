/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.common.event

class EventPublisher {
    private val observers: MutableMap<String, MutableList<EventObserver<Any>>> = mutableMapOf()

    fun subscribe(key: String, observer: EventObserver<Any>) {
        observers.getOrPut(key) {
            mutableListOf()
        }.add(observer)
    }

    fun unsubscribe(key: String, observer: EventObserver<Any>) {
        observers[key]?.remove(observer)
    }

    fun sendEvent(key: String,event:Any) {
        observers[key]?.forEach { it:EventObserver<Any>->
            it.onEvent(event)
        }
    }

}