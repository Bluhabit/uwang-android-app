/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signup

import com.bluhabit.core.ui.components.textfield.TextFieldState
import javax.annotation.concurrent.Immutable

@Immutable
data class SignUpState(
    //global
    // 0 == sing up 1 == otp
    val currentScreen: Int = 0,
    //sign up
    val emailState: String = "",
    val emailInputState:TextFieldState=TextFieldState.None,
    val signUpButtonEnabled: Boolean = true,

    // Otp Sign Up Screen
    val otpSentCountDown: Long = 120_000L,
    val otpNumberState: String = "",
    val otpNumberError: Boolean = false,
    val otpNumberEnabled: Boolean = true,
    val otpSentAlertVisibility: Boolean = false,
    val otpSentAlertSuccess: Boolean = false,
    val otpSentLimit: Boolean = false,
    val isAccountLocked: Boolean = false,
    val verifyOtpButtonEnabled: Boolean = true,

    //create password
    val passwordState: String = "",
    val passwordInputState: TextFieldState = TextFieldState.None,
    val passwordVisibility: Boolean = false,
    val confirmPasswordState: String = "",
    val confirmPasswordInputState: TextFieldState = TextFieldState.None,
    val passwordConfirmationVisibility: Boolean = false
)