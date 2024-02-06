/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.textfield

sealed class TextFieldState {
    object None : TextFieldState()
    data class WithHint(val hintText: String) : TextFieldState()
    data class Error(val errorText: String) : TextFieldState()
    data class Success(val successText: String) : TextFieldState()
}