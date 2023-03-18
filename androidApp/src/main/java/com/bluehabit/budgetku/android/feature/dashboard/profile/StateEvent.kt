/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.profile

data class ProfileState(
    var name:String=""
)

sealed class ProfileEvent{
    class SetName(var name:String):ProfileEvent()
}