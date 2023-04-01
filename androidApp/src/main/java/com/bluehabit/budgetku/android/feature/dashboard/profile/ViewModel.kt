/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.profile

import com.bluehabit.budgetku.android.base.BaseViewModel
import com.bluehabit.budgetku.android.base.BaseViewModelData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
) : BaseViewModelData<ProfileState,ProfileDataState, ProfileEvent>(ProfileState(),ProfileDataState()) {
    init {
        handleActions()
    }

    override fun handleActions() = onEvent {
        when (it) {
            is ProfileEvent.SetName -> {
            }
        }
    }


}