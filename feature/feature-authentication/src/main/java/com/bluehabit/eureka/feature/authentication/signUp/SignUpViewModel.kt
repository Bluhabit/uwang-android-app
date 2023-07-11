/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.signUp

import android.util.Patterns
import app.trian.mvi.ui.viewModel.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

) : MviViewModel<SignUpState,SignUpIntent, SignUpAction>(SignUpState()) {


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
               // controller.context.hideKeyboard()
               // controller.showBottomSheet()
            }
        }
    }


//    private fun signUpGoogle(
//        result: Task<GoogleSignInAccount>?
//    ) = async {
//
//    }




    override fun onAction(action: SignUpAction) {
        when (action) {
            SignUpAction.Submit -> signUpWithEmail()
//            is SignUpEvent.SignUpWithGoogle -> signUpGoogle(it.result)
        }
    }
}