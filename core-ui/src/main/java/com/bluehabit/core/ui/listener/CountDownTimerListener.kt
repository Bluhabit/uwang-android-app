/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.listener


fun interface CountDownTimerListener {
    fun onNotify(isTimeout:Boolean, vararg data:String)
}