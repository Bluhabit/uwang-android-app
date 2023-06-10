/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.listener


sealed interface  ScreenToAppEvent {
    object EXIT_APP:ScreenToAppEvent

}


fun interface AppStateEventListener {
    fun onEvent(event:ScreenToAppEvent)
}