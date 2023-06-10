/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.checkEmailResetPassword

import com.bluehabit.core.ui.extensions.navigateUp
import com.bluehabit.core.ui.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckEmailResetPasswordViewModel @Inject constructor(

) : BaseViewModel<CheckEmailResetPasswordState, CheckEmailResetPasswordEvent>(CheckEmailResetPasswordState()) {

    init {
        handleActions()
    }

    override fun handleActions() = onEvent {
        when (it) {
            is CheckEmailResetPasswordEvent.OpenEmailApplication ->controller.showSnackbar("Open email app")
            is CheckEmailResetPasswordEvent.ChangeEmailForResetPassword -> controller.navigateUp()
        }
    }
}


