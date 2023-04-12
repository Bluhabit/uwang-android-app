/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.checkEmailResetPassword

import com.bluehabit.budgetku.android.base.BaseViewModel
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
            is CheckEmailResetPasswordEvent.OpenEmailApplication -> showSnackbar("Open email app")
            is CheckEmailResetPasswordEvent.ChangeEmailForResetPassword -> navigateUp()
        }
    }
}


