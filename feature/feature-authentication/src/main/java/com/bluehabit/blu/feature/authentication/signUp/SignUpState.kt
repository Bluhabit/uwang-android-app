/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.blu.feature.authentication.signUp

import android.os.Parcelable
import app.trian.mvi.ui.internal.contract.MviState
import javax.annotation.concurrent.Immutable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Immutable
@Parcelize
data class SignUpState(
    val isLoading: Boolean = false,


    //end complete profile
    override val effect: @RawValue SignUpEffect = SignUpEffect.Nothing
) : MviState<SignUpEffect>(), Parcelable
