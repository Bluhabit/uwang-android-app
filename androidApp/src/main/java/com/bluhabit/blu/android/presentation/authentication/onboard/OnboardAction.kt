/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.onboard

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

sealed interface OnboardAction {
    object CheckSession : OnboardAction
    data class SignInGoogle(
        val task: Task<GoogleSignInAccount>
    ) : OnboardAction
}