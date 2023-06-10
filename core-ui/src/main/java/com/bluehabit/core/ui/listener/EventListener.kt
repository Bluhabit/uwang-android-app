/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.listener

import androidx.compose.material.ModalBottomSheetValue


class EventListener {
    private var appEvent: AppStateEventListener? = null
    private var bottomSheetStateListener: BottomSheetStateListener? = null
    private var countDownTimerListener: CountDownTimerListener?=null

    //timer
    fun addTimerListener(listener: CountDownTimerListener){
        countDownTimerListener=listener
    }
    fun updateTimer(isTimeout:Boolean,vararg value:String){
        countDownTimerListener?.onNotify(isTimeout,*value)
    }
    //end timer
    //region app event
    fun addOnEventListener(listener: AppStateEventListener) {
        appEvent = listener
    }

    fun sendEvent(eventName: ScreenToAppEvent) {
        appEvent?.onEvent(eventName)
    }
    //end region

    //region bottom sheet
    fun addOnBottomSheetStateListener(listener: BottomSheetStateListener) {
        bottomSheetStateListener = listener
    }

    fun changeBottomSheetState(state: ModalBottomSheetValue) {
        bottomSheetStateListener?.onStateChanges(state)
    }
    //end region

    fun clear() {
        appEvent = null
        bottomSheetStateListener = null
    }


}
