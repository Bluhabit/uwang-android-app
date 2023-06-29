/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.createNewPassword

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class CreateNewPasswordState(
    val password: String = "",
    val confirmPassword: String = "",
    val isButtonEnabled : Boolean = false,
    val isInputPasswordError: Boolean = false,
    val isConfirmPasswordError: Boolean = false,
    val errorInputPassword: String = "",
    val errorConfirmPassword: String = ""
) : Parcelable

