/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.changePassword

import android.os.Parcelable
import app.trian.mvi.ui.extensions.Empty
import app.trian.mvi.ui.internal.contract.MviState
import com.bluehabit.eureka.data.authentication.AuthConstant
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ChangePasswordState(
    val currentScreen: Int = AuthConstant.PROFILE_SCREEN_CHANGE_PASSWORD,
    val email: String = String.Empty,
    val emailError:Boolean=false,
    val emailErrorMessage:String=String.Empty,

    val password: String = String.Empty,
    val passwordError:Boolean=false,
    val passwordErrorMessage:String=String.Empty,

    val confirmPassword: String = String.Empty,
    val confirmPasswordError:Boolean=false,
    val confirmPasswordErrorMessage:String=String.Empty,

    val isLoading:Boolean=false,
    override val effect: @RawValue ChangePasswordEffect = ChangePasswordEffect.Nothing
) : MviState<ChangePasswordEffect>(), Parcelable