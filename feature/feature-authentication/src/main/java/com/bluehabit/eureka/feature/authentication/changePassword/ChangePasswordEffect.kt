/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.changePassword

sealed interface  ChangePasswordEffect {

    data class ShowAlert(val message:String): ChangePasswordEffect

    object Nothing : ChangePasswordEffect
}