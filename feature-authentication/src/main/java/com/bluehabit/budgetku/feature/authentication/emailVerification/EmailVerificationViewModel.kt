/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.emailVerification

import app.trian.mvi.ui.viewModel.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmailVerificationViewModel @Inject constructor(
) : MviViewModel<EmailVerificationState, EmailVerificationIntent, EmailVerificationAction>(
    EmailVerificationState(),
) {

    override fun onAction(action: EmailVerificationAction) {

    }

}