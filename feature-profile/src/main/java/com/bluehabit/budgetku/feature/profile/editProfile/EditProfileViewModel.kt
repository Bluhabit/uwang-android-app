/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.profile.editProfile

import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.budgetku.feature.profile.editProfile.EditProfileAction
import com.bluehabit.budgetku.feature.profile.editProfile.EditProfileIntent
import com.bluehabit.budgetku.feature.profile.editProfile.EditProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
) : MviViewModel<EditProfileState, EditProfileIntent, EditProfileAction>(EditProfileState()) {
    override fun onAction(action: EditProfileAction) {

    }

}