/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.signUp

import android.os.Parcelable
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class SignUpState(
    val fullName:String="",
    val email:String="",
    val password:String=""
) : Parcelable

sealed class SignUpEvent{

    object SignUpWithEmail:SignUpEvent()
    class SignUpWithGoogle(var result: Task<GoogleSignInAccount>?):SignUpEvent()

    class SetFullName(var fullName:String):SignUpEvent()
    class SetEmail(var email:String):SignUpEvent()
    class SetPassword(var password:String):SignUpEvent()



}