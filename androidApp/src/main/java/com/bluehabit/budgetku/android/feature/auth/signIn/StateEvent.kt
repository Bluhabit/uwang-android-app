/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.signIn

import android.os.Parcelable
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class SignInState(
    val email: String = "",
    val password: String = ""
) : Parcelable

sealed class SignInEvent {
    object SignInWithEmail: SignInEvent()
    class SignInWithGoogle(var result: Task<GoogleSignInAccount>?): SignInEvent()
}