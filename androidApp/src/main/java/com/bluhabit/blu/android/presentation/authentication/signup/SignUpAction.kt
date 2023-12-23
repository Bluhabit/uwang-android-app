/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signup

sealed interface SignUpAction {

    data class EmailAction (
        val value: String = "",
        val error: Boolean = false
    ) : SignUpAction
    data class PasswordAction(
        val value: String = "",
        val visibility: Boolean = false,
        val error: Boolean = false,
    ) : SignUpAction

    data class PasswordConfirmationAction(
        val value: String = "",
        val visibility: Boolean = false,
        val error: Boolean = false,
    ) : SignUpAction

    data class ButtonAction(
        val enabled: Boolean = true,
    ) : SignUpAction
}