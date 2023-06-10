/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.emailVerification

import com.bluehabit.core.ui.viewModel.BaseViewModelData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmailVerificationViewModel @Inject constructor(
) : BaseViewModelData<EmailVerificationState, EmailVerificationDataState, EmailVerificationEvent>(
    EmailVerificationState(),
    EmailVerificationDataState()
) {
    init {
        handleActions()
    }

    override fun handleActions() = onEvent {}

}