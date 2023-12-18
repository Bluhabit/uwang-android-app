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
    val emailState: String = "",
    val emailError: Boolean = false,
    val passwordState: String = "",
    val passwordVisibility: Boolean = false,
    val passwordError: Boolean = false,
    val buttonEnabled: Boolean = true,
)