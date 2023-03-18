/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.signUp

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

data class SignUpState(
    var fullName:String="",
    var email:String="",
    var password:String=""
)

sealed class SignUpEvent{

    object SignUpWithEmail:SignUpEvent()
    class SignUpWithGoogle(var result: Task<GoogleSignInAccount>?):SignUpEvent()

    class SetFullName(var fullName:String):SignUpEvent()
    class SetEmail(var email:String):SignUpEvent()
    class SetPassword(var password:String):SignUpEvent()



}