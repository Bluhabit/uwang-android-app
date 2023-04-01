/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.signUp

import com.bluehabit.budgetku.android.base.BaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

) : BaseViewModel<SignUpState, SignUpEvent>(SignUpState()) {

    init {
        handleActions()
    }

    private fun signUpWithEmail(
    ) = async {
//        authSDK.signUpWithEmail(
//            uiState.value.fullName, uiState.value.email, uiState.value.password
//        ).collect {
//            when (it) {
//                is Response.Error -> app.showSnackbar(it.message)
//                Response.Loading -> Unit
//                is Response.Result -> app.showSnackbar(it.data.message)
//            }
//        }
    }


    private fun signUpGoogle(
        result: Task<GoogleSignInAccount>?
    ) = async {
//        if (result != null) {
//            val token = result.await()
//            authSDK.signUpGoogle(token.idToken.orEmpty()).collect {
//                when (it) {
//                    is Response.Error -> app.showSnackbar(it.message)
//                    Response.Loading -> Unit
//                    is Response.Result -> app.showSnackbar(it.data.message)
//                }
//            }
//        } else {
//            app.showSnackbar("Cancel by provider")
//        }

    }


    override fun handleActions()  = onEvent {
        when (it) {
            is SignUpEvent.SetEmail -> {

            }
            is SignUpEvent.SetFullName -> {

            }
            is SignUpEvent.SetPassword -> {

            }
            SignUpEvent.SignUpWithEmail -> signUpWithEmail()
            is SignUpEvent.SignUpWithGoogle -> signUpGoogle(it.result)
        }
    }
}