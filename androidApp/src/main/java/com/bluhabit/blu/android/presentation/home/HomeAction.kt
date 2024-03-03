/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.home

sealed interface HomeAction {
    // Not Empty
    data class OnScreenChange(
        val screen: Int,
    ): HomeAction
}