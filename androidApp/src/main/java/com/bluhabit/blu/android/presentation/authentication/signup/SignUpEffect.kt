/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signup


sealed interface SignUpEffect {
    object None : SignUpEffect

    object NavigateToPersonalize : SignUpEffect
}