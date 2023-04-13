/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.createNewPassword

import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateNewPasswordViewModel @Inject constructor(

) : BaseViewModel<CreateNewPasswordState, CreateNewPasswordEvent>(CreateNewPasswordState()) {

    init {
        handleActions()
    }

    private fun validateInputPassword(
        valid: suspend (Boolean, String) -> Unit
    ) = asyncWithState {
        when {
            password.isEmpty() -> valid(false, getString(R.string.subtitle_error_password_empty))
            password.length < 8 -> valid(false, getString(R.string.subtitle_error_password_length))
            else -> valid(true, "")
        }
    }

    private fun validateConfirmPassword(
        valid: suspend (Boolean, String) -> Unit
    ) = asyncWithState {
        when {
            confirmPassword != password -> valid(false, getString(R.string.subtitle_error_password_not_identical))
            confirmPassword.isEmpty() -> valid(false, getString(R.string.subtitle_error_password_empty))
            else -> valid(true, "")
        }
    }

    private fun handleButtonSave(
        callback: suspend (Boolean) -> Unit
    ) = asyncWithState {
        when {
            !isInputPasswordError && !isConfirmPasswordError -> callback(true)
        }
    }

    override fun handleActions() = onEvent {
        when (it) {
            is CreateNewPasswordEvent.ValidatePassword -> validateInputPassword { isValid, errorMessage ->
                commit {
                    copy(
                        isInputPasswordError = !isValid,
                        errorInputPassword = errorMessage,
                    )
                }
            }
            is CreateNewPasswordEvent.ValidateConfirmPassword -> validateConfirmPassword { isValid, errorMessage ->
                commit {
                    copy(
                        isConfirmPasswordError = !isValid,
                        errorConfirmPassword = errorMessage,
                    )
                }
            }
            is CreateNewPasswordEvent.HandleButtonSaveChanges -> handleButtonSave {
                commit { copy(isButtonEnabled = it) }
            }
            is CreateNewPasswordEvent.ChangeToNewPassword -> {
                showSnackbar("Change password to ${it.password}")
            }
        }
    }
}


