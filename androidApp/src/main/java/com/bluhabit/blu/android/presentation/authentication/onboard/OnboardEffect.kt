/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.onboard

sealed interface OnboardEffect {
    object None:OnboardEffect
    data class ShowDialog(
        val message: String
    ) : OnboardEffect
}