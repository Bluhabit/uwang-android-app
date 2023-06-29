/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.checkEmailResetPassword

sealed interface CheckEmailResetPasswordAction {
    object OpenEmailApplication : CheckEmailResetPasswordAction
    object ChangeEmailForResetPassword : CheckEmailResetPasswordAction
}