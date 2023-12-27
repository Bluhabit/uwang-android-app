/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.forgotPassword

import javax.annotation.concurrent.Immutable

@Immutable
data class ForgotPasswordState(
    //global
    val currentScreen:Int=0,

    val otpButtonEnabled:Boolean=false,

    val otpState:String="",
    val otpError:Boolean=false,
    val otpErrorText:String="",

    val emailState: String="",
    val emailError: Boolean = false,
    val emailErrorText: String = "",

    val passwordState:String="",
    val passwordError:Boolean=false,
    val passwordErrorText:String="",
    val passwordVisibility:Boolean=false,

    val confirmPasswordState:String="",
    val confirmPasswordError:Boolean=false,
    val confirmPasswordErrorText:String="",
    val confirmPasswordVisibility:Boolean=false,

    val nextButtonEnabled: Boolean = true,
)