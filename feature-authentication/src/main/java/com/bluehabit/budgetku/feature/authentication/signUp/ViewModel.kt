/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.signUp

import android.util.Patterns
import app.hilwa.ar.base.extensions.hideKeyboard
import com.bluehabit.core.ui.extensions.showBottomSheet
import com.bluehabit.core.ui.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

) : BaseViewModel<SignUpState, SignUpEvent>(SignUpState()) {

    init {
        handleActions()
    }

    private fun signUpWithEmail(
    ) = asyncWithState {
        when {
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                commit {
                    copy(
                        isEmailValid = false,
                        errorMessage = "Email tidak cocok"
                    )
                }
            }

            else -> {
                commit {
                    copy(
                        isEmailValid = true,
                        errorMessage = ""
                    )
                }
                controller.context.hideKeyboard()
                controller.showBottomSheet()
            }
        }
    }


//    private fun signUpGoogle(
//        result: Task<GoogleSignInAccount>?
//    ) = async {
//
//    }


    override fun handleActions() = onEvent {
        when (it) {
            SignUpEvent.Submit -> signUpWithEmail()
//            is SignUpEvent.SignUpWithGoogle -> signUpGoogle(it.result)
        }
    }
}