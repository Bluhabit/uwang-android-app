/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.resetPassword

import javax.annotation.concurrent.Immutable

@Immutable
data class ForgotPasswordState(
    val emailState: String="",
    val emailError: Boolean = false,
    val emailErrorText:  String = "",
    val passwordState: String = "",
    val passwordVisibility: Boolean = false,
    val nextButtonEnabled: Boolean = true,
    val passwordError: Boolean = false,
)