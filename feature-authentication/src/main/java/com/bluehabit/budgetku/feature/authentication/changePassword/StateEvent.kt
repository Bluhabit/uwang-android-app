/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.changePassword

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class ChangePasswordState(
    val oldPassword: String = "",
    val newPassword: String = "",
    val confirmNewPassword: String = "",
    val isButtonEnabled : Boolean = false,
    val isInputOldPasswordError: Boolean = false,
    val isInputNewPasswordError: Boolean = false,
    val isConfirmNewPasswordError: Boolean = false,
    val errorInputOldPassword: String = "",
    val errorInputNewPassword: String = "",
    val errorConfirmNewPassword: String = ""
) : Parcelable

sealed class ChangePasswordEvent {
    object ValidateOldPassword : ChangePasswordEvent()
    object ValidateNewPassword : ChangePasswordEvent()
    object ValidateConfirmNewPassword : ChangePasswordEvent()
    object HandleButtonSaveChanges : ChangePasswordEvent()
    class ChangeToNewPassword(var password: String) : ChangePasswordEvent()
}