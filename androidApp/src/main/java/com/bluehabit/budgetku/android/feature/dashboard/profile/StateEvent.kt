/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.profile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class ProfileState(
    val name:String=""
) : Parcelable

@Immutable
@Parcelize
data class ProfileDataState(
    val name:String=""
) : Parcelable
sealed class ProfileEvent{
    class SetName(var name:String):ProfileEvent()
}