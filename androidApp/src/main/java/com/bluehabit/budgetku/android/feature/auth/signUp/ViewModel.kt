/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.signUp

import android.util.Patterns
import com.bluehabit.budgetku.android.base.BaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

) : BaseViewModel<SignUpState, SignUpEvent>(SignUpState()) {

    init {
        handleActions()
    }

    private fun signUpWithEmail(
    ) = asyncWithState {
        when{
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() ->{
                commit {
                    copy(
                        isEmailValid = false,
                        errorMessage = "Email tidak cocok"
                    )
                }
            }
            else ->{
                commit {
                    copy(
                        isEmailValid = true,
                        errorMessage = ""
                    )
                }
                hideKeyboard()
                showBottomSheet()
            }
        }
    }


    private fun signUpGoogle(
        result: Task<GoogleSignInAccount>?
    ) = async {

    }


    override fun handleActions()  = onEvent {
        when (it) {
            SignUpEvent.Submit -> signUpWithEmail()
            is SignUpEvent.SignUpWithGoogle -> signUpGoogle(it.result)
        }
    }
}