/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.base

import com.bluehabit.budgetku.android.base.listener.AppBarListener
import com.bluehabit.budgetku.android.base.listener.AppStateEventListener
import com.bluehabit.budgetku.android.base.listener.BottomSheetListener
import com.bluehabit.budgetku.android.base.listener.SnackbarListener


class EventListener {
    private var appEvent: AppStateEventListener? = null
    private var appBarListener: AppBarListener? = null
    private var snackBarListener: SnackbarListener? = null
    private var bottomSheetListener: BottomSheetListener? = null


    //region app event
    fun addOnEventListener(listener: AppStateEventListener) {
        appEvent = listener
    }

    fun sendEvent(eventName: String) {
        appEvent?.onEvent(eventName)
    }
    //end region
    //region app bar
    fun addOnAppBarListener(listener: AppBarListener) {
        appBarListener = listener
    }

    fun actionItemClicked(id: String,vararg params: String) {
        appBarListener?.onActionItemClicked(id, *params)
    }

    fun navigationButtonClicked() {
        appBarListener?.onNavigationButtonClicked()
    }

    fun navigationItemClick(id: String, vararg params: String) {
        appBarListener?.onNavigationItemClicked(
            id,
            *params
        )
    }

    fun fabClicked(id: String, vararg params: String) {
        appBarListener?.onFabClicked(
            id,
            *params
        )
    }
    //end region
    //region snackbar

    fun addSnackbarBarListener(listener: SnackbarListener) {
        snackBarListener = listener
    }

    fun snackbarContent(id: String, params: Array<out String>) {
        snackBarListener?.onContent(id, *params)
    }

    fun snackbarAction(id: String) {
        snackBarListener?.onAction(id)
    }

    //end region
    //region bottom sheet
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
    //end bottom sheet

    fun clear(){
        appEvent = null
        appBarListener = null
        bottomSheetListener = null
        snackBarListener = null
    }

}
