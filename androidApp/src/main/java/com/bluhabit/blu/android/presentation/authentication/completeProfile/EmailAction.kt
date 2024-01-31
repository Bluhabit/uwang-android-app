/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.completeProfile

import javax.annotation.concurrent.Immutable

@Immutable
data class EditProfileActivity (
    val profileState: String="",
    val profileError: Boolean = false,
    val profileErrorText: String = "",
    val nextButtonEnabled: Boolean = true,
)