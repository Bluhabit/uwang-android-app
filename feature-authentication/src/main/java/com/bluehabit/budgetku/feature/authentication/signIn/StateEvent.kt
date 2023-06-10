/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.signIn

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class SignInState(
    val email: String = "",
    val password: String = "",
    var emailIsError: Boolean=false,
    var passwordIsError: Boolean=false,
) : Parcelable

sealed class SignInEvent {
    object SignInWithEmail: SignInEvent()
    class OnEmailChange(var email: String) : SignInEvent()
    class OnPasswordChange(var password: String) : SignInEvent()
//    class SignInWithGoogle(var result: Task<GoogleSignInAccount>?): SignInEvent()
}