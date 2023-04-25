/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createAccountSaving

import com.bluehabit.budgetku.android.base.BaseViewModelData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateAccountSavingViewModel @Inject constructor(
) : BaseViewModelData<CreateAccountSavingState, CreateAccountSavingDataState, CreateAccountSavingEvent>(
    CreateAccountSavingState(),
    CreateAccountSavingDataState()
) {
    init {
        handleActions()
    }

    private fun onNext() = asyncWithState {
        if (screenType < 4) {
            commit {
                copy(
                    screenType = screenType + 1
                )
            }
        }
    }

    private fun onPrev() = asyncWithState {
        if (screenType > 0) {
            commit {
                copy(
                    screenType = screenType - 1
                )
            }
        }
    }

    override fun handleActions() = onEvent {
        when (it) {
            CreateAccountSavingEvent.Next -> onNext()
            CreateAccountSavingEvent.Prev -> onPrev()
        }
    }

}