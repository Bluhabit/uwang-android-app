/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.editProfile

import android.os.Parcelable
import com.bluehabit.budgetku.data.remote.dummy.dummyAvatar
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class EditProfileState(
    val fullName: String = "Mario Jr.",
    val dateOfBirth: String = "16 September 1998",
    val gender: String = "Laki - Laki",
    val image: Int = com.bluehabit.budgetku.data.R.drawable.dummy_avatar_1,
    val bottomSheetType:String="",
) : Parcelable

@Immutable
@Parcelize
data class EditProfileDataState(
    val avatars: List<Int> = dummyAvatar
) : Parcelable

sealed interface EditProfileEvent {
}