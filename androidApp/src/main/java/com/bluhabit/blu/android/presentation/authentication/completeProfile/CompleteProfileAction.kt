/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.completeProfile

sealed interface CompleteProfileAction {

    data class InputDobScreenDateAction(
        val value: String = "",
        val error: Boolean = false
    ) : CompleteProfileAction
    data class InputDobScreenNextButtonAction(
        val enabled: Boolean = true,
    ) : CompleteProfileAction

    data class SetPreferenceScreenPreferenceItem(
        val checked: Boolean = false,
        val index: Int = 0,
    ) : CompleteProfileAction
}