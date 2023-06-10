/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.listener

import androidx.compose.material.ModalBottomSheetValue

fun interface BottomSheetStateListener {
    fun onStateChanges(state:ModalBottomSheetValue)
}