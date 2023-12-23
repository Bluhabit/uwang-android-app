/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signin

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

sealed interface SignInAction {

    data class EmailAction(
        val value: String = "",
        val error: Boolean = false
    ) : SignInAction

    data class PasswordAction(
        val value: String = "",
        val visibility: Boolean = false,
        val error: Boolean = false,
    ) : SignInAction

    data class ButtonAction(
        val enabled: Boolean = true,
    ) : SignInAction

    data class SignInGoogle(
        val authResult:Task<GoogleSignInAccount>
    ) : SignInAction
}