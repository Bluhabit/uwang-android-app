/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.createNewPassword

import android.annotation.SuppressLint
import android.content.Context
import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.core.ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class CreateNewPasswordViewModel @Inject constructor(
     @ApplicationContext private val context: Context
) : MviViewModel<CreateNewPasswordState,CreateNewPasswordIntent, CreateNewPasswordAction>(CreateNewPasswordState()) {

    private fun validate(valid: suspend (Boolean) -> Unit) = asyncWithState {
        when {
            password.isEmpty() -> {
                commit {
                    copy(
                        isInputPasswordError = true,
                        errorInputPassword = context.getString(R.string.subtitle_error_password_empty)
                    )
                }
                valid(false)
            }

            password.length < 8 -> {
                commit {
                    copy(
                        isInputPasswordError = true,
                        errorInputPassword = context.getString(R.string.subtitle_error_password_length)
                    )
                }
                valid(false)
            }

            confirmPassword != password -> {
                commit {
                    copy(
                        isConfirmPasswordError = true,
                        errorConfirmPassword = context.getString(R.string.subtitle_error_password_not_identical)
                    )
                }
                valid(false)
            }

            confirmPassword.isEmpty() -> {
                commit {
                    copy(
                        isConfirmPasswordError = true,
                        errorConfirmPassword = context.getString(R.string.subtitle_error_password_empty)
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


    override fun onAction(action: CreateNewPasswordAction) {
        when (action) {
            CreateNewPasswordAction.Submit -> validate {
                if(it){
                    // controller.navigateSingleTop(Home.routeName)
                }
            }
        }
    }
}


