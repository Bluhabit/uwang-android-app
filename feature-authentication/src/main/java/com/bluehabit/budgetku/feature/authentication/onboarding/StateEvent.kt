/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.onboarding

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class OnboardState(
    val percentage:Float=01f
) : Parcelable


sealed class OnboardEvent{
    data class PagerChanges(val page:Int=0): OnboardEvent()

}