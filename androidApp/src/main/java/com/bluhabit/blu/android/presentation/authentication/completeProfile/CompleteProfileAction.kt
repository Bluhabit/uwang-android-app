/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.completeProfile

import android.graphics.Bitmap
import com.bluhabit.blu.android.presentation.authentication.completeProfile.screen.SelectedTopic
import java.time.LocalDate

sealed interface CompleteProfileAction {
    data class OnUsernameChange(
        val value:String=""
    ):CompleteProfileAction

    data class OnImageChange(
        val value:Bitmap
    ):CompleteProfileAction

    data class OnScreenChange(
        val screen:Int=0
    ):CompleteProfileAction
    object SubmitData:CompleteProfileAction

    object NextStep:CompleteProfileAction

    data class OnDateOfBirthChange(
        val value: LocalDate? = null
    ) : CompleteProfileAction
    data class SetPreferenceScreenPreferenceItem(
        val checked: Boolean = false,
        val index: Int = 0,
    ) : CompleteProfileAction

    // Upload Photo Profile Screen
    data class OnProfileImageChange(
        val value: Bitmap,
    ) : CompleteProfileAction
    data class OnShowDialogChoice (
        val show: Boolean,
    ) : CompleteProfileAction
    // Choose Topic Screen
    object OnClearSelectedList: CompleteProfileAction
    data class OnAddSelectedList(
        val topic: SelectedTopic,
    ) : CompleteProfileAction
    data class OnRemoveSelectedList(
        val topic: SelectedTopic,
    ) : CompleteProfileAction
}