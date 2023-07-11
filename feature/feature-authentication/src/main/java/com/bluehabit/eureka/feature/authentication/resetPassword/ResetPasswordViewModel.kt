/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.resetPassword

import android.util.Patterns
import app.trian.mvi.ui.viewModel.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(

) : MviViewModel<ResetPasswordState,ResetPasswordIntent, ResetPasswordAction>(ResetPasswordState()) {


    private fun validateData(
        valid: suspend (Boolean) -> Unit
    ) = asyncWithState {
        when {
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.isEmpty() -> {
                if (!isButtonEnabled) return@asyncWithState
                commit { copy(isButtonEnabled = false) }
            }
            else -> valid(true)
        }
    }

    override fun onAction(action: ResetPasswordAction) {
        when (action) {
            is ResetPasswordAction.ValidateEmailAddress -> validateData {
                commit { copy(isButtonEnabled = it) }
            }
            is ResetPasswordAction.SendLinkResetPasswordToEmail -> {
              //  controller.showSnackbar("Mengirimkan ke  ${action.email}")
            }
        }
    }
}


