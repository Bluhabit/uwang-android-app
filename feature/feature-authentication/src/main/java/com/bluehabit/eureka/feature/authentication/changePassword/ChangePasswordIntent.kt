/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.changePassword

sealed interface ChangePasswordIntent {
    object ValidateOldPassword : ChangePasswordIntent
    object ValidateNewPassword : ChangePasswordIntent
    object ValidateConfirmNewPassword : ChangePasswordIntent
    object HandleButtonSaveChanges : ChangePasswordIntent
    class ChangeToNewPassword(var password: String) : ChangePasswordIntent
}