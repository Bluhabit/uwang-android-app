/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.base

import com.bluehabit.budgetku.android.base.listener.AppBarSelectedListener
import com.bluehabit.budgetku.android.base.listener.AppStateEventListener
import com.bluehabit.budgetku.android.base.listener.BottomAppBarClickListener
import com.bluehabit.budgetku.android.base.listener.BottomSheetListener
import com.bluehabit.budgetku.android.base.listener.SnackBarClickListener


class EventListener {
    private var appEvent: AppStateEventListener? = null
    private var topAppBarListener: AppBarSelectedListener? = null
    private var bottomAppBarListener: BottomAppBarClickListener? = null
    private var snackBarClickListener: SnackBarClickListener? = null
    private var bottomSheetListener: BottomSheetListener? = null


    fun addOnEventListener(listener: AppStateEventListener) {
        appEvent = listener
    }

    fun sendEvent(eventName: String) {
        appEvent?.onEvent(eventName)
    }

    fun addOnAppBarListener(listener: AppBarSelectedListener) {
        topAppBarListener = listener
    }

    fun topAppBarAction(id: String, params: Array<out String>) {
        topAppBarListener?.onAction(id, *params)
    }

    fun onNavigationIconClicked() {
        topAppBarListener?.onNavigationIconClicked()
    }

    fun addOnBottomAppBarListener(listener: BottomAppBarClickListener) {
        bottomAppBarListener = listener
    }

    fun bottomAppBarClick(id: String,vararg params: String) {
        bottomAppBarListener?.onItemClick(
            id,
            *params
        )
    }

    fun addSnackbarBarListener(listener: SnackBarClickListener) {
        snackBarClickListener = listener
    }

    fun snackbarContent(id: String, params: Array<out String>) {
        snackBarClickListener?.onContent(id, *params)
    }

    fun snackbarAction(id: String) {
        snackBarClickListener?.onAction(id)
    }

    fun addOnBottomSheetListener(listener: BottomSheetListener) {
        bottomSheetListener = listener
    }

    fun bottomSheetAction(id: String, params: Array<out String>) {
        bottomSheetListener?.onAction(id, *params)
    }

    fun bottomSheetContent(id: String, params: Array<out String>) {
        bottomSheetListener?.onContentClick(
            id,
            *params
        )
    }

    fun bottomSheetClose() {
        bottomSheetListener?.onClose()
    }

    fun clear(){
        appEvent = null
        topAppBarListener = null
        bottomSheetListener = null
        bottomAppBarListener = null
        snackBarClickListener = null
    }

}
