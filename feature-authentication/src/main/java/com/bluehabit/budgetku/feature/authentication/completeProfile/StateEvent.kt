/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.completeProfile

import android.os.Parcelable
import com.bluehabit.core.ui.components.bottomSheet.Gender
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class CompleteProfileState(
    val contentBottomSheet: String = "",
    val fullName:String="",
    val dateOfBirth: LocalDate? = null,
    val gender: Gender?=null
) : Parcelable

sealed class CompleteProfileEvent {
}