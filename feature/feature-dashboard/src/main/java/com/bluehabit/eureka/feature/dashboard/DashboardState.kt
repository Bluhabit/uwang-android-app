/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.dashboard

import android.os.Parcelable
import app.trian.mvi.ui.extensions.Empty
import app.trian.mvi.ui.internal.contract.MviState
import com.bluehabit.eureka.feature.dashboard.DashboardEffect
import javax.annotation.concurrent.Immutable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Immutable
@Parcelize
data class DashboardState(
    val fullName: String = String.Empty,
    val dashboardScreen: Int = 0,
    override val effect: @RawValue DashboardEffect = DashboardEffect.Nothing
) : MviState<DashboardEffect>(), Parcelable

