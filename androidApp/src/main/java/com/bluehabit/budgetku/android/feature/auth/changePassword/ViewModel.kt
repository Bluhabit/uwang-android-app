/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.changePassword

import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(

) : BaseViewModel<ChangePasswordState, ChangePasswordEvent>(ChangePasswordState()) {

    init {
        handleActions()
    }

    private fun validateInputOldPassword(
        valid: suspend (Boolean, String) -> Unit,
    ) = asyncWithState {
        when {
            oldPassword.isEmpty() -> valid(false, getString(R.string.subtitle_error_password_empty_change_password))
            else -> valid(true, "")
        }
    }

    private fun validateInputNewPassword(
        valid: suspend (Boolean, String) -> Unit
    ) = asyncWithState {
        when {
            newPassword.isEmpty() -> valid(false, getString(R.string.subtitle_error_password_empty_change_password))
            newPassword.length < 8 -> valid(false, getString(R.string.subtitle_error_password_length_change_password))
            else -> valid(true, "")
        }
    }

    private fun validateConfirmNewPassword(
        valid: suspend (Boolean, String) -> Unit
    ) = asyncWithState {
        when {
            confirmNewPassword != newPassword -> valid(false, getString(R.string.subtitle_error_password_not_identical_change_password))
            confirmNewPassword.isEmpty() -> valid(false, getString(R.string.subtitle_error_password_empty_change_password))
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

    override fun handleActions() = onEvent {
        when (it) {
            is ChangePasswordEvent.ValidateOldPassword -> validateInputOldPassword { isValid, errorMessage ->
                commit {
                    copy(
                        isInputOldPasswordError = !isValid,
                        errorInputOldPassword = errorMessage
                    )
                }
            }
            is ChangePasswordEvent.ValidateNewPassword -> validateInputNewPassword { isValid, errorMessage ->
                commit {
                    copy(
                        isInputNewPasswordError = !isValid,
                        errorInputNewPassword = errorMessage,
                    )
                }
            }
            is ChangePasswordEvent.ValidateConfirmNewPassword -> validateConfirmNewPassword { isValid, errorMessage ->
                commit {
                    copy(
                        isConfirmNewPasswordError = !isValid,
                        errorConfirmNewPassword = errorMessage,
                    )
                }
            }
            is ChangePasswordEvent.HandleButtonSaveChanges -> handleButtonSave {
                commit { copy(isButtonEnabled = it) }
            }
            is ChangePasswordEvent.ChangeToNewPassword -> {
                showSnackbar("Change password to ${it.password}")
            }
        }
    }
}