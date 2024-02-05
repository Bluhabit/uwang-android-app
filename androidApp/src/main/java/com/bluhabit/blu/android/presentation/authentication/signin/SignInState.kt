/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signin

import com.bluhabit.core.ui.components.textfield.TextFieldState
import javax.annotation.concurrent.Immutable

@Immutable
data class SignInState(
    //global
    val showLoading:Boolean=false,
    // 0 == input 1 == otp
    val currentScreen: Int =0,
    val showButtonResendOtp:Boolean=false,
    // Sign In Screen
    val emailState: String = "",
    val emailInputState:TextFieldState = TextFieldState.None,

    val passwordState: String = "",
    val passwordInputState:TextFieldState=TextFieldState.None,

    val passwordVisibility: Boolean = false,
    // Otp Sign In Screen
    val otpSentCountDown: Long = 120_000L,
    val otpNumberState: String = "",
    val otpNumberInputState:TextFieldState=TextFieldState.None,

    val otpNumberEnabled: Boolean = true,
    val otpSentAlertVisibility: Boolean = false,
    val otpSentAlertSuccess: Boolean = false,

    val otpSentLimit: Boolean = false,
    val otpAttempt: Int = 1,
    val verifyOtpButtonEnabled: Boolean = true,
)