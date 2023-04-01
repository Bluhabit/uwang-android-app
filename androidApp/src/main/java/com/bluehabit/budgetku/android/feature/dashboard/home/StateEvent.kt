/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class HomeState(
    val name: String = ""
) : Parcelable

@Immutable
@Parcelize
data class HomeDataState(
    var a:List<String> = listOf()
) : Parcelable

sealed class HomeEvent {
    class SetName(var name: String) : HomeEvent()
}