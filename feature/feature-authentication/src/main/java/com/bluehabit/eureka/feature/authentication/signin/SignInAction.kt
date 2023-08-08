/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.signin

sealed interface SignInAction {
    object Nothing : SignInAction
    object CheckSession : SignInAction
    object Submit : SignInAction
}