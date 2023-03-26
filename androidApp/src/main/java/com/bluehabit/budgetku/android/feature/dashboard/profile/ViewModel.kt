/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.profile

import com.bluehabit.budgetku.android.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
) : BaseViewModel<ProfileState, ProfileEvent>(ProfileState()) {
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