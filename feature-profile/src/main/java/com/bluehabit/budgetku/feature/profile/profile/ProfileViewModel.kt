/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.profile.profile

import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.budgetku.feature.profile.profile.ProfileAction
import com.bluehabit.budgetku.feature.profile.profile.ProfileIntent
import com.bluehabit.budgetku.feature.profile.profile.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
) : MviViewModel<ProfileState, ProfileIntent, ProfileAction>(ProfileState()) {
    override fun onAction(action: ProfileAction) {
        TODO("Not yet implemented")
    }

}