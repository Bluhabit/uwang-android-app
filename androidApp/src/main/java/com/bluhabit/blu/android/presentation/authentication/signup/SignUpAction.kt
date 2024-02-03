/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signup

import java.time.LocalDate

sealed interface SignUpAction {
    data class OnScreenChange(
        val screen: Int = 0,
    ) : SignUpAction

    data class OnShowBottomSheet(
        val show:Boolean=true,
        val type:BottomSheetSignUpType
    ) : SignUpAction

    data class OnEmailChange(
        val value: String = ""
    ) : SignUpAction

    data class OnFullNameChange(
        val value: String = ""
    ) : SignUpAction

    data class OnDateOfBirthChange(
        val value: LocalDate
    ) : SignUpAction

    data class OnGenderChange(
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

    data class OnButtonEnabledChange(
        val enabled: Boolean = false,
    ) : SignUpAction

    // Otp Sign Up Screen
    data class OnOtpChange(
        val value: String = ""
    ) : SignUpAction

    data class OnSentOtpAlertVisibilityChange(
        val visibility: Boolean = false,
    ) : SignUpAction

    object SignUpBasic : SignUpAction
    object OnCountDownStart : SignUpAction
    object OnResendOtp : SignUpAction
    object OnVerifyOtp : SignUpAction
}