/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.home

data class HomeState(
    var name: String = ""
)

sealed class HomeEvent {
    class SetName(var name: String) : HomeEvent()
}