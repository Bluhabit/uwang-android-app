/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.resetPassword

import com.bluehabit.eureka.feature.authentication.signUp.SignUpEffect

sealed interface ResetPasswordEffect {
    data class ShowAlert(val message:String): ResetPasswordEffect
    object Nothing : ResetPasswordEffect
}