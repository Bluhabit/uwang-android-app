/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.createNewPassword

import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateNewPasswordViewModel @Inject constructor(

) : BaseViewModel<CreateNewPasswordState, CreateNewPasswordEvent>(CreateNewPasswordState()) {

    init {
        handleActions()
    }

    private fun validate(valid: suspend (Boolean) -> Unit) = asyncWithState {
        when {
            password.isEmpty() -> {
                commit {
                    copy(
                        isInputPasswordError = true,
                        errorInputPassword = controller.context.getString(R.string.subtitle_error_password_empty)
                    )
                }
                valid(false)
            }

            password.length < 8 -> {
                commit {
                    copy(
                        isInputPasswordError = true,
                        errorInputPassword = controller.context.getString(R.string.subtitle_error_password_length)
                    )
                }
                valid(false)
            }

            confirmPassword != password -> {
                commit {
                    copy(
                        isConfirmPasswordError = true,
                        errorConfirmPassword = controller.context.getString(R.string.subtitle_error_password_not_identical)
                    )
                }
                valid(false)
            }

            confirmPassword.isEmpty() -> {
                commit {
                    copy(
                        isConfirmPasswordError = true,
                        errorConfirmPassword = controller.context.getString(R.string.subtitle_error_password_empty)
                    )
                }
            }
            else -> {
                commit {
                    copy(
                        isInputPasswordError = false,
                        isConfirmPasswordError = false
                    )
                }
                valid(true)
            }
        }
    }



    override fun handleActions() = onEvent { event ->
        when (event) {
            CreateNewPasswordEvent.Submit -> validate {
                if(it){
                   // controller.navigateSingleTop(Home.routeName)
                }
            }
        }
    }
}


