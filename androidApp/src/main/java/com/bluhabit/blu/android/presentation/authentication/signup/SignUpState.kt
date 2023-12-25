/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signup

import com.bluhabit.core.ui.ext.Empty
import javax.annotation.concurrent.Immutable

@Immutable
data class SignUpState(
    //global
    // 0 == sing up 1 == otp
    val currentScreen:Int=1,
    //sign up
    val emailState: String = "",
    val emailError: Boolean = false,
    val emailErrorText: String = "",
    val passwordState: String = "",
    val passwordVisibility: Boolean = false,
    val passwordError: Boolean = false,
    val passwordErrorText: String = "",
    val passwordConfirmationState: String = "",
    val passwordConfirmationVisibility: Boolean = false,
    val passwordConfirmationError: Boolean = false,
    val passwordConfirmationErrorText: String = "",
    val buttonEnabled: Boolean = true,
    //otp
    val otpState: String = String.Empty,
    val otpError: Boolean = false,
    val email: String = String.Empty,
    //end otp
)