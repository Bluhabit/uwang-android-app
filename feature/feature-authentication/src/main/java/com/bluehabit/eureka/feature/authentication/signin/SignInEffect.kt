/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.signin

sealed interface SignInEffect {
    object Nothing : SignInEffect

    object NavigateToHome : SignInEffect
}