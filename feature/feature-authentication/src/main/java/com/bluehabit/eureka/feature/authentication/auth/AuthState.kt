/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.auth

import android.os.Parcelable
import app.trian.mvi.ui.extensions.Empty
import app.trian.mvi.ui.internal.contract.MviState
import com.bluehabit.eureka.data.authentication.AuthConstant.AUTH_SCREEN_SIGN_IN
import com.bluehabit.eureka.data.authentication.AuthConstant.AUTH_SCREEN_SIGN_UP
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class AuthState(
    val emailSignIn: String = String.Empty,
    val emailSignUp: String = String.Empty,
    val passwordSignIn: String = String.Empty,
    val isRememberChecked: Boolean = false,

    val isLoading: Boolean = false,

    //tabs
    val tabs: @RawValue List<Pair<String, Int>> = listOf(
        Pair("Masuk", AUTH_SCREEN_SIGN_IN),
        Pair("Daftar", AUTH_SCREEN_SIGN_UP)
    ),
    val selectedTab: Int = AUTH_SCREEN_SIGN_IN,
    //
    override val effect: @RawValue AuthEffect = AuthEffect.Nothing
) : MviState<AuthEffect>(), Parcelable
