/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.base

import com.bluehabit.budgetku.android.base.listener.AppStateEventListener


class EventListener {
    private var appEvent: AppStateEventListener? = null

    //region app event
    fun addOnEventListener(listener: AppStateEventListener) {
        appEvent = listener
    }

    fun sendEvent(eventName: String) {
        appEvent?.onEvent(eventName)
    }
    //end region


    fun clear(){
        appEvent = null
    }


}
