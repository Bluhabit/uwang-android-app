/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.dashboard.report

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class ReportState(
    val a: String = ""
) : Parcelable


@Immutable
@Parcelize
data class ReportsDataState(
    val a: String = ""
) : Parcelable



sealed class ReportEvent {
}