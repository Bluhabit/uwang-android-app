/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.resetPassword

sealed interface ForgotPasswordAction {
    data class EmailAction(
        val value: String = "",
        val error: Boolean = false,
    ) : ForgotPasswordAction

    data class NextButtonAction(
        val enabled: Boolean = true,
    ) : ForgotPasswordAction
}