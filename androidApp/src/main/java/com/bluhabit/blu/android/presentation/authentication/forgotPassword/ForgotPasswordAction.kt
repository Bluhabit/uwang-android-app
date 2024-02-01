/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.forgotPassword

sealed interface ForgotPasswordAction {
    data class OnScreenChange(
        val screen: Int = 0
    ) : ForgotPasswordAction

    data class OnEmailChange(
        val value: String = ""
    ) : ForgotPasswordAction


    data class OnNewPasswordChange(
        val value: String = ""
    ) : ForgotPasswordAction

    data class OnNewPasswordVisibilityChange(
        val visibility: Boolean = false
    ) : ForgotPasswordAction

    data class OnConfirmPasswordChange(
        val value: String = ""
    ) : ForgotPasswordAction

    data class OnConfirmPasswordVisibilityChange(
        val visibility: Boolean = false
    ) : ForgotPasswordAction

    data class NextButtonAction(
        val enabled: Boolean = true,
    ) : ForgotPasswordAction

    object ForgotPassword : ForgotPasswordAction
    object VerifyOtp : ForgotPasswordAction
    object SetNewPassword : ForgotPasswordAction

    //    [new] - otp
    data class OnOtpChange(
        val value: String = ""
    ) : ForgotPasswordAction

    data class OnSentOtpAlertVisibilityChange(
        val visibility: Boolean = false,
    ) : ForgotPasswordAction

    object OnCountDownStart : ForgotPasswordAction
    object OnResentOtp : ForgotPasswordAction
    object OnVerifyOtp : ForgotPasswordAction
}