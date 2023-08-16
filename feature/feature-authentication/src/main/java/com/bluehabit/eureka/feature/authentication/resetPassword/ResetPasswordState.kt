/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.resetPassword

import android.os.Parcelable
import app.trian.mvi.ui.internal.contract.MviState
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class ResetPasswordState(
    val currentScreen: Int = 1,
    override val effect: @RawValue ResetPasswordEffect = ResetPasswordEffect.Nothing
) : MviState<ResetPasswordEffect>(), Parcelable