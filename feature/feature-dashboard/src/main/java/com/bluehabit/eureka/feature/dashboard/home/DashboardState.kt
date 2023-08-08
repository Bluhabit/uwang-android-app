/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.dashboard.home

import android.os.Parcelable
import app.trian.mvi.ui.internal.contract.MviState
import com.bluehabit.core.ui.routes.Routes
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.math.BigDecimal
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class DashboardState(
    override val effect: @RawValue DashboardEffect = DashboardEffect.Nothing
) :MviState<DashboardEffect>(), Parcelable

