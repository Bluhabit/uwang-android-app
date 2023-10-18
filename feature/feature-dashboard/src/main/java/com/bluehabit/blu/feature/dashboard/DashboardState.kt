/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.blu.feature.dashboard

import android.os.Parcelable
import app.trian.mvi.ui.internal.contract.MviState
import javax.annotation.concurrent.Immutable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Immutable
@Parcelize
data class DashboardState(
    val dashboardScreen: Int = 0,

    //end home
    override val effect: @RawValue DashboardEffect = DashboardEffect.Nothing
) : MviState<DashboardEffect>(), Parcelable

