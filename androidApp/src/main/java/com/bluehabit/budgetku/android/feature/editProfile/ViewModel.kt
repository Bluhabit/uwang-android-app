/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.editProfile

import com.bluehabit.budgetku.android.base.BaseViewModelData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
) : BaseViewModelData<EditProfileState, EditProfileDataState, EditProfileEvent>(EditProfileState(), EditProfileDataState()) {
    init {
        handleActions()
    }

    override fun handleActions() = onEvent {}

}