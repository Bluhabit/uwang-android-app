/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.blu.feature.authentication.signUp

sealed interface SignUpEffect{
    object Nothing:SignUpEffect
    data class ShowAlert(val message:String):SignUpEffect
    object NavigateToHome:SignUpEffect
}