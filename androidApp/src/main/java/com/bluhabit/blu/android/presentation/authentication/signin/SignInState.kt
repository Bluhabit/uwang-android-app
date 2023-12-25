/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signin

import javax.annotation.concurrent.Immutable

@Immutable
data class SignInState(
    //global
    // 0 == input 1 == otp
    val currentScreen: Int = 0,
    // Sign In Screen
    val emailState: String = "",
    val emailError: Boolean = false,
    val passwordState: String = "",
    val passwordVisibility: Boolean = false,
    val passwordError: Boolean = false,
    val buttonEnabled: Boolean = true,
    // Otp Sign In Screen
    val otpNumberState: String = "",
    val otpNumberError: Boolean = false,
    val otpButtonEnabled: Boolean = true,
)