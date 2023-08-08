/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.signin

import android.os.Parcelable
import app.trian.mvi.ui.extensions.Empty
import app.trian.mvi.ui.internal.contract.MviState
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class SignInState(
    val email: String = String.Empty,
    val password: String = String.Empty,
    //
    override val effect: @RawValue SignInEffect = SignInEffect.Nothing
) :MviState<SignInEffect>(), Parcelable
