/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signin

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

sealed interface AuthEffect {
    object Nothing : AuthEffect
    object NavigateToHome : AuthEffect
    object NavigateToOtp : AuthEffect
    data class ShowDialog(val message:String):AuthEffect
}