/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.blu.feature.authentication.signUp

sealed interface SignUpAction {
    object Nothing : SignUpAction
    object SubmitOtp : SignUpAction
    object SubmitCompleteProfile : SignUpAction
    data class OnFullNameChange(val value:String) : SignUpAction
    data class OnPasswordChange(val value:String) : SignUpAction
    data class OnConfirmPasswordChange(val value:String) : SignUpAction
}