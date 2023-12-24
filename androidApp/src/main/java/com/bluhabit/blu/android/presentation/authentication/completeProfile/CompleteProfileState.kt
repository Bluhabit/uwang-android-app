/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.completeProfile

import com.bluhabit.core.ui.ext.Empty
import javax.annotation.concurrent.Immutable

@Immutable
data class CompleteProfileState(
    // Input Dob Screen
    val otpDobScreenDateState: String = String.Empty,
    val otpDobScreenDateStateError: Boolean = false,
    val otpDobScreenNextButtonEnabled: Boolean = true,
)