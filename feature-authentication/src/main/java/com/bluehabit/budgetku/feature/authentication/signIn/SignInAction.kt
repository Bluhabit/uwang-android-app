/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.signIn

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

sealed interface SignInAction {
    object SignInWithEmail: SignInAction
    class OnEmailChange(var email: String) : SignInAction
    class OnPasswordChange(var password: String) : SignInAction
    class SignInWithGoogle(var result: Task<GoogleSignInAccount>?): SignInAction
}