/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.changePassword

sealed interface ChangePasswordAction {
    object ValidateOldPassword : ChangePasswordAction
    object ValidateNewPassword : ChangePasswordAction
    object ValidateConfirmNewPassword : ChangePasswordAction
    object HandleButtonSaveChanges : ChangePasswordAction
    class ChangeToNewPassword(var password: String) : ChangePasswordAction
}