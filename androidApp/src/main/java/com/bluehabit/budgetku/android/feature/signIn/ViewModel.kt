/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.signIn

import android.util.Patterns
import com.bluehabit.budgetku.android.base.BaseViewModel
import com.bluehabit.budgetku.data.common.Response
import com.bluehabit.budgetku.data.domain.auth.SignInWIthGoogleUseCase
import com.bluehabit.budgetku.data.domain.auth.SignInWithEmailUseCase
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val signInWIthGoogleUseCase: SignInWIthGoogleUseCase
) : BaseViewModel<SignInState, SignInEvent>(SignInState()) {

    init {
        handleActions()
    }

    private fun validateData(
        valid:suspend (String,String)->Unit
    ) = asyncWithState {
        when{
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> showSnackbar("Email didn't valid")
            else->valid(email,password)
        }
    }

    private fun handelResponse(response:Response<String>) = async {
        when(response){
            is Response.Error -> Unit
            Response.Loading -> Unit
            is Response.Result -> Unit
        }
    }

    override fun handleActions() = onEvent {
        when (it) {
            SignInEvent.SignInWithEmail -> validateData { email, password ->
                signInWithEmailUseCase(email,password).collect(::handelResponse)
            }
            is SignInEvent.SignInWithGoogle ->  signInWIthGoogleUseCase(it.result?.await()?.idToken).collect(::handelResponse)
        }
    }
}


