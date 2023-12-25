/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signup

sealed interface SignUpAction {
    data class OnScreenChange(
        val screen: Int = 0,
    ) : SignUpAction

    data class OnEmailChange(
        val value: String = ""
    ) : SignUpAction

    data class OnPasswordChange(
        val value: String = ""
    ) : SignUpAction

    data class OnPasswordVisibilityChange(
        val visibility: Boolean = false
    ) : SignUpAction

    data class OnPasswordConfirmationChange(
        val value: String = ""
    ) : SignUpAction

    data class OnPasswordConfirmationVisibilityChange(
        val visibility: Boolean = false
    ) : SignUpAction

    data class OnOtpChange(
        val otp: String="",
    ) : SignUpAction
    object SignUpBasic : SignUpAction
    object VerifyOtpUpBasic : SignUpAction
}