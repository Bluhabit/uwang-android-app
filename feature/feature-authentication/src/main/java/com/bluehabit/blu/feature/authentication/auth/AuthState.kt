/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.blu.feature.authentication.auth

import android.os.Parcelable
import app.trian.mvi.ui.extensions.Empty
import app.trian.mvi.ui.internal.contract.MviState
import javax.annotation.concurrent.Immutable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Immutable
@Parcelize
data class AuthState(
    //sign in
    val emailSignIn: String = String.Empty,
    val isRememberChecked: Boolean = false,
    val passwordSignIn: String = String.Empty,
    val emailSignInError: Boolean = false,
    val passwordSignInError: Boolean = false,

    //end
    //sign up
    val emailSignUp: String = String.Empty,
    val emailSignUpError: Boolean = false,

    //end
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = String.Empty,
    //
    override val effect: @RawValue AuthEffect = AuthEffect.Nothing
) : MviState<AuthEffect>(), Parcelable
