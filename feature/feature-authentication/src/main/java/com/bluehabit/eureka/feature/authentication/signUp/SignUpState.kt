/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.signUp

import android.os.Parcelable
import app.trian.mvi.ui.extensions.Empty
import app.trian.mvi.ui.internal.contract.MviState
import com.bluehabit.eureka.data.authentication.AuthConstant.AUTH_SCREEN_OTP
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class SignUpState(
    val currentScreen: Int = AUTH_SCREEN_OTP,
    val showDialogConfirmation: Boolean = false,
    val isLoading: Boolean = false,
    val isAlertError: Boolean = false,
    //otp
    val otp: String = String.Empty,
    val email: String = String.Empty,
    //end otp
    //complete profile
    val fullName: String = String.Empty,
    val password: String = String.Empty,
    val confirmPassword: String = String.Empty,

    //end complete profile
    override val effect: @RawValue SignUpEffect = SignUpEffect.Nothing
) : MviState<SignUpEffect>(), Parcelable
