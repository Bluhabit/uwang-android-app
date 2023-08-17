/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.auth

sealed interface AuthAction {
    object Nothing : AuthAction
    object CheckSession : AuthAction
    object SignInWithEmail : AuthAction
    object SignUpWithEmail : AuthAction
}