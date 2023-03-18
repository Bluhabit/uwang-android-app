/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.signIn

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

data class SignInState(
    var email: String = "",
    var password: String = ""
)

sealed class SignInEvent {
    object SignInWithEmail:SignInEvent()
    class SignInWithGoogle(var result: Task<GoogleSignInAccount>?):SignInEvent()
    class SetEmail(var email:String):SignInEvent()
    class SetPassword(var password:String):SignInEvent()
}