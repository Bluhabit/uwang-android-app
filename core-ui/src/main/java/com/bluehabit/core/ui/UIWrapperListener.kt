/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui

import app.hilwa.ar.base.extensions.hideKeyboard
import com.bluehabit.core.ui.extensions.addOnBottomSheetStateChangeListener
import com.bluehabit.core.ui.extensions.backPressedAndClose
import com.bluehabit.core.ui.extensions.hideBottomSheet
import com.bluehabit.core.ui.extensions.navigate
import com.bluehabit.core.ui.extensions.navigateAndReplace
import com.bluehabit.core.ui.extensions.navigateAndReplaceAll
import com.bluehabit.core.ui.extensions.navigateSingleTop
import com.bluehabit.core.ui.extensions.navigateUp
import com.bluehabit.core.ui.extensions.showBottomSheet
import com.bluehabit.core.ui.listener.BottomSheetStateListener

open class UIWrapperListener<State, Event>(
    val controller: UIController,
    private val state: State,
    private val commit: (State) -> Unit = {},
    private val dispatcher: (Event) -> Unit = {},
) {
    fun commit(s: State.() -> State) {
        this.commit(s(state))
    }

    fun dispatch(e: Event) {
        this.dispatcher(e)
    }

    //application state nav
    //region navigation
    fun backAndClose() =
        controller.backPressedAndClose()

    fun navigateUp() =
        controller.navigateUp()

    fun navigate(routeName: String, vararg args: String) =
        controller.navigate(routeName, *args)

    fun navigateSingleTop(routeName: String, vararg args: String) =
        controller.navigateSingleTop(routeName, *args)

    fun navigateSingleTop(routeName: String) =
        controller.navigateSingleTop(routeName)

    fun navigateAndReplace(routeName: String, vararg args: String) =
        controller.navigateAndReplace(routeName, *args)

    fun navigateAndReplaceAll(routeName: String, vararg args: String) =
        controller.navigateAndReplaceAll(routeName, *args)

    fun navigateAndReplaceAll(routeName: String) =
        controller.navigateAndReplaceAll(routeName)

    //end region
    //region bottomsheet
    fun hideBottomSheet() =
        controller.hideBottomSheet()

    fun showBottomSheet() =
        controller.showBottomSheet()

    fun addOnBottomSheetStateChangeListener(listener: BottomSheetStateListener) =
        controller.addOnBottomSheetStateChangeListener(listener)
    //end region

    //keyboard
    fun hideKeyboard() =
        controller.context.hideKeyboard()
    //end region

    //snackbar
    //region snakcbar

    fun showSnackbar(message: String) =
        controller.showSnackbar(message)
    fun showSnackbar(message: Int) =
        controller.showSnackbar(message)
    fun showSnackbar(message: Int, vararg params: String) =
        controller.showSnackbar(message, *params)
    //

    //end region
    //end
}
class UIWrapperListenerData<State, Data, Event>(
    controller: UIController,
    state: State,
    private val data: Data,
    commit: (State) -> Unit = {},
    private val commitData: (Data) -> Unit = {},
    dispatcher: (Event) -> Unit = {},
) : UIWrapperListener<State, Event>(
    controller = controller,
    state = state,
    commit = commit,
    dispatcher = dispatcher
) {
    fun commitData(d: Data.() -> Data) {
        this.commitData(d(data))
    }
}
