/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.signUp

import android.os.Parcelable
import app.trian.mvi.ui.internal.contract.MviState
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class SignUpState(
    val currentScreen:Int=1,
    override val effect: @RawValue SignUpEffect = SignUpEffect.Nothing
) : MviState<SignUpEffect>(), Parcelable
