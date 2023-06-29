/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.changePassword

import android.content.Context
import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.core.ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : MviViewModel<ChangePasswordState, ChangePasswordIntent,ChangePasswordAction>(ChangePasswordState()) {



    private fun validateInputOldPassword(
        valid: suspend (Boolean, String) -> Unit,
    ) = asyncWithState {
        when {
            oldPassword.isEmpty() -> valid(false, context.getString(R.string.subtitle_error_password_empty_change_password))
            else -> valid(true, "")
        }
    }

    private fun validateInputNewPassword(
        valid: suspend (Boolean, String) -> Unit
    ) = asyncWithState {
        when {
            newPassword.isEmpty() -> valid(false, context.getString(R.string.subtitle_error_password_empty_change_password))
            newPassword.length < 8 -> valid(false, context.getString(R.string.subtitle_error_password_length_change_password))
            else -> valid(true, "")
        }
    }

    private fun validateConfirmNewPassword(
        valid: suspend (Boolean, String) -> Unit
    ) = asyncWithState {
        when {
            confirmNewPassword != newPassword -> valid(false, context.getString(R.string.subtitle_error_password_not_identical_change_password))
            confirmNewPassword.isEmpty() -> valid(false, context.getString(R.string.subtitle_error_password_empty_change_password))
            else -> valid(true, "")
        }
    }

    private fun handleButtonSave(
        callback: suspend (Boolean) -> Unit
    ) = asyncWithState {
        when {
            !isInputNewPasswordError && !isConfirmNewPasswordError -> callback(true)
        }
    }
    override fun onAction(action: ChangePasswordAction) {
        when (action) {
            is ChangePasswordAction.ValidateOldPassword -> validateInputOldPassword { isValid, errorMessage ->
                commit {
                    copy(
                        isInputOldPasswordError = !isValid,
                        errorInputOldPassword = errorMessage
                    )
                }
            }
            is ChangePasswordAction.ValidateNewPassword -> validateInputNewPassword { isValid, errorMessage ->
                commit {
                    copy(
                        isInputNewPasswordError = !isValid,
                        errorInputNewPassword = errorMessage,
                    )
                }
            }
            is ChangePasswordAction.ValidateConfirmNewPassword -> validateConfirmNewPassword { isValid, errorMessage ->
                commit {
                    copy(
                        isConfirmNewPasswordError = !isValid,
                        errorConfirmNewPassword = errorMessage,
                    )
                }
            }
            is ChangePasswordAction.HandleButtonSaveChanges -> handleButtonSave {
                commit { copy(isButtonEnabled = it) }
            }
            is ChangePasswordAction.ChangeToNewPassword -> {
                //controller.showSnackbar("Change password to ${it.password}")
            }
        }
    }
}