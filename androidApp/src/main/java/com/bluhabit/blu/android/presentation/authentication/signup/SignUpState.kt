/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signup

import javax.annotation.concurrent.Immutable

@Immutable
data class SignUpState(
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
)