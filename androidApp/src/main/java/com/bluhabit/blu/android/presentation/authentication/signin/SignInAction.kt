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
    data class OnScreenChange(
        val screen: Int = 0
    ) : SignInAction


    // Sign In Screen
    data class OnEmailChange(
        val value: String = ""
    ) : SignInAction

    data class OnPasswordChange(
        val value: String = "",
    ) : SignInAction

    data class OnPasswordVisibilityChange(
        val visibility: Boolean = false
    ) : SignInAction

    object OnSignInBasic : SignInAction
    object OnVerifyOtp : SignInAction

    data class OnSignInGoogle(
        val authResult: Task<GoogleSignInAccount>
    ) : SignInAction

    // Otp Sign In Screen

    data class OnButtonEnabledChange(
        val enabled:Boolean=false
    ):SignInAction
    data class OnOtpChange(
        val value: String = ""
    ) : SignInAction
}