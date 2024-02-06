/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signup

import com.bluhabit.core.ui.components.textfield.TextFieldState
import java.time.LocalDate
import javax.annotation.concurrent.Immutable

enum class BottomSheetSignUpType {
    GENDER,
    DATE_OF_BIRTH
}

@Immutable
data class SignUpState(
    //global
    // 0 == sing up 1 == otp
    val currentScreen: Int = 0,
    val showButtonResendOtp:Boolean=false,
    val showLoading: Boolean = false,
    val bottomSheetType: BottomSheetSignUpType = BottomSheetSignUpType.DATE_OF_BIRTH,
    //[NEW] sign up
    val emailState: String = "",
    val emailInputState: TextFieldState = TextFieldState.None,
    val signUpButtonEnabled: Boolean = true,

    // Otp Sign Up Screen
    val otpSentCountDown: Long = 120_000L,
    val otpNumberState: String = "",
    val otpNumberInputState:TextFieldState=TextFieldState.None,

    val otpNumberEnabled: Boolean = true,
    val otpSentAlertVisibility: Boolean = false,
    val otpSentAlertSuccess: Boolean = false,
    val otpSentLimit: Boolean = false,
    val otpAttempt: Int = 1,
    val verifyOtpButtonEnabled: Boolean = true,

    //[NEW] Complete Profile
    val fullNameState: String = "",
    val fullNameInputState: TextFieldState = TextFieldState.None,
    val dateOfBirthState: LocalDate? = null,
    val dateOfBirthInputState: TextFieldState = TextFieldState.None,
    val genderState: String = "",
    val genderInputState: TextFieldState = TextFieldState.None,

    //create password
    val passwordState: String = "",
    val passwordInputState: TextFieldState = TextFieldState.None,
    val passwordVisibility: Boolean = false,
    val confirmPasswordState: String = "",
    val confirmPasswordInputState: TextFieldState = TextFieldState.None,
    val passwordConfirmationVisibility: Boolean = false
)