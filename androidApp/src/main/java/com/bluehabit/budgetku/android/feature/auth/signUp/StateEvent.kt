/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.signUp

import android.os.Parcelable
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class SignUpState(
    val email:String="",
    val password:String="",
    val isEmailValid:Boolean=true,
    val errorMessage:String=""
) : Parcelable

sealed interface SignUpEvent{

    object Submit: SignUpEvent
    class SignUpWithGoogle(var result: Task<GoogleSignInAccount>?): SignUpEvent
}