/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.personalization

sealed interface PersonalizationEffect {
    object None:PersonalizationEffect

    object NavigateToMain:PersonalizationEffect
}