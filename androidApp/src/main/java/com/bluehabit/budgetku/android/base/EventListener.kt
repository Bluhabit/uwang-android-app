/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.base

import androidx.compose.material.ModalBottomSheetValue
import com.bluehabit.budgetku.android.base.listener.BottomNavigationListener
import com.bluehabit.budgetku.android.base.listener.BottomSheetStateListener
import com.bluehabit.budgetku.android.base.listener.ToAppStateEventListener
import com.bluehabit.budgetku.android.components.DashboardBottomNavigationMenu


class EventListener {
    private var toAppEvent: ToAppStateEventListener? = null
    private var bottomNavigationEvent: BottomNavigationListener? = null
    private var bottomSheetStateListener: BottomSheetStateListener? = null

    fun bottomNavigationListener(listener: BottomNavigationListener){
        bottomNavigationEvent = listener
    }

    fun refresh(item:DashboardBottomNavigationMenu) = bottomNavigationEvent?.onRefresh(item)
    fun navigate(item:DashboardBottomNavigationMenu) = bottomNavigationEvent?.onNavigate(item)
    fun onFab() = bottomNavigationEvent?.onFab()

    //region app event
    fun addOnEventListener(listener: ToAppStateEventListener) {
        toAppEvent = listener
    }

    fun sendEvent(eventName: String) {
        toAppEvent?.onEvent(eventName)
    }

    fun exit() {
        toAppEvent?.exit()
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
        toAppEvent = null
        bottomSheetStateListener = null
    }


}