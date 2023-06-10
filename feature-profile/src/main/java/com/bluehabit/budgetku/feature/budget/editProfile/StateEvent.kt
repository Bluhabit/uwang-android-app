/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.editProfile

import android.os.Parcelable
import com.bluehabit.budgetku.data.remote.dummy.dummyAvatar
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class EditProfileState(
    val fullName: String = "Mario Jr.",
    val dateOfBirth: String = LocalDate.of(
        1998,
        9,
        16
    ).toString(),
    val gender: String = "Laki - Laki",
    val image: Int = com.bluehabit.budgetku.data.R.drawable.dummy_avatar_1,
    val bottomSheetType: String = "",
) : Parcelable

@Immutable
@Parcelize
data class EditProfileDataState(
    val avatars: List<Int> = dummyAvatar
) : Parcelable

sealed interface EditProfileEvent {
}