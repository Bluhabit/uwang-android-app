/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.termAndCondition

import com.bluhabit.blu.android.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TermAndConditionViewModel
@Inject constructor(): BaseViewModel<TermAndConditionState, TermAndConditionAction, TermAndConditionEffect>(
    TermAndConditionState()
) {
    override fun onAction(action: TermAndConditionAction) {
        TODO("Not yet implemented")
    }
}