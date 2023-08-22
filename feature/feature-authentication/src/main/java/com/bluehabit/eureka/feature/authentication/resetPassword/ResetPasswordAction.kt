/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.resetPassword

sealed interface ResetPasswordAction {

    data class OnEmailChange(val value:String):ResetPasswordAction
    data class OnPasswordChange(val value:String):ResetPasswordAction
    data class OnConfirmPasswordChange(val value:String):ResetPasswordAction
    object SubmitRequestResetPassword:ResetPasswordAction

    object SubmitCreateNewPassword:ResetPasswordAction
}