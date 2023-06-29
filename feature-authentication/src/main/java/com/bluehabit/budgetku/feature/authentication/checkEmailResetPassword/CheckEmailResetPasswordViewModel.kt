/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.checkEmailResetPassword

import app.trian.mvi.ui.viewModel.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckEmailResetPasswordViewModel @Inject constructor(

) : MviViewModel<CheckEmailResetPasswordState, CheckEmailResetPasswordIntent,CheckEmailResetPasswordAction>(CheckEmailResetPasswordState()) {

    override fun onAction(action: CheckEmailResetPasswordAction) {
        when (action) {
            is CheckEmailResetPasswordAction.OpenEmailApplication ->{}//controller.showSnackbar("Open email app")
            is CheckEmailResetPasswordAction.ChangeEmailForResetPassword -> {}//controller.navigateUp()
        }
    }
}


