/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.signUp

sealed interface SignUpIntent{

    object Submit: SignUpIntent
//    class SignUpWithGoogle(var result: Task<GoogleSignInAccount>?): SignUpEvent
}