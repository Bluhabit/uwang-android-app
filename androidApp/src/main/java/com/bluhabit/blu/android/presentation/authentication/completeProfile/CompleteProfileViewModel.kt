/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.completeProfile

import com.bluhabit.blu.android.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompleteProfileViewModel
@Inject constructor(): BaseViewModel<CompleteProfileState, CompleteProfileAction, CompleteProfileEffect>(
    CompleteProfileState()
) {
    override fun onAction(action: CompleteProfileAction) {
        when (action) {
            is CompleteProfileAction.InputDobScreenNextButtonAction -> updateState { copy(otpDobScreenNextButtonEnabled = action.enabled) }
            is CompleteProfileAction.InputDobScreenDateAction -> updateState { copy(
                otpDobScreenDateState = action.value,
                otpDobScreenDateStateError = action.error
            ) }
        }
    }
}