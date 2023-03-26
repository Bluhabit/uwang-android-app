/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.base

import androidx.compose.material.ModalBottomSheetValue
import com.bluehabit.budgetku.android.base.listener.AppStateEventListener
import com.bluehabit.budgetku.android.base.listener.BottomSheetStateListener


class EventListener {
    private var appEvent: AppStateEventListener? = null
    private var bottomSheetStateListener: BottomSheetStateListener? = null

    //region app event
    fun addOnEventListener(listener: AppStateEventListener) {
        appEvent = listener
    }

    fun sendEvent(eventName: String) {
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