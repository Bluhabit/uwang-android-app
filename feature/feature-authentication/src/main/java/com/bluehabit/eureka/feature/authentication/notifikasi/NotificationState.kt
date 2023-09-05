/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.notifikasi

import android.os.Parcelable
import app.trian.mvi.ui.extensions.Empty
import app.trian.mvi.ui.internal.contract.MviState
import com.bluehabit.eureka.data.authentication.AuthConstant
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class NotificationState(
    val currentScreen: Int = AuthConstant.EMPTY_NOTIFICATION,
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
    override val effect: @RawValue NotificationEffect = NotificationEffect.Nothing
) : MviState<NotificationEffect>(), Parcelable