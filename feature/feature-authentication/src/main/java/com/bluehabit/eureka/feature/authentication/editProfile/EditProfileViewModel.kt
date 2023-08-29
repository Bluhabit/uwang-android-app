/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.editProfile

import app.trian.mvi.ui.viewModel.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class EditProfileViewModel(
):MviViewModel<EditProfileState,EditProfileAction>(
    EditProfileState()
) {
    override fun onAction(action: EditProfileAction) {
        TODO("Not yet implemented")
    }

}