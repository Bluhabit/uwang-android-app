/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.forgotPassword

import com.bluhabit.core.ui.components.textfield.TextFieldState
import javax.annotation.concurrent.Immutable

@Immutable
data class ForgotPasswordState(
    //global
    val currentScreen: Int = 0,
    val showLoading:Boolean=false,
    val showButtonResendOtp:Boolean=false,

    // Otp Forgot Password Screen
    val otpSentCountDown: Long = 120_000L,
    val otpNumberState: String = "",
    val otpNumberInputState:TextFieldState=TextFieldState.None,
    val otpNumberEnabled: Boolean = true,
    val otpSentAlertVisibility: Boolean = false,
    val otpSentAlertSuccess: Boolean = false,
    val otpSentLimit: Boolean = false,
    val otpAttempt: Int = 1,
    val verifyOtpButtonEnabled: Boolean = true,

    val emailState: String = "",
    val emailInputState: TextFieldState = TextFieldState.None,

    val passwordState: String = "",
    val passwordInputState: TextFieldState = TextFieldState.WithHint("Password minimal terdiri dari 8 karakter"),
    val passwordVisibility: Boolean = false,

    val confirmPasswordState: String = "",
    val confirmPasswordInputState: TextFieldState = TextFieldState.WithHint("Password harus sama"),
    val confirmPasswordVisibility: Boolean = false,

    val nextButtonEnabled: Boolean = true,
)