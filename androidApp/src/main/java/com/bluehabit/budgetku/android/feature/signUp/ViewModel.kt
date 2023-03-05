/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluehabit.budgetku.sdk.auth.AuthSDK
import com.bluehabit.budgetku.utils.Response
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authSDK: AuthSDK
) : ViewModel() {

    fun signUpWithEmail(
        fullName: String,
        email: String,
        password: String,
        cb: suspend (Boolean, String) -> Unit
    ) = with(viewModelScope) {
        launch {
            authSDK.signUpWithEmail(
                fullName, email, password
            ).collect {
                when (it) {
                    is Response.Error -> cb(false, it.message)
                    Response.Loading -> Unit
                    is Response.Result -> cb(true, it.data.message)
                }
            }
        }
    }

    fun signUpGoogle(
        result: Task<GoogleSignInAccount>?,
        cb: suspend (Boolean, String) -> Unit
    ) = with(viewModelScope) {
        launch {
            if (result != null) {
                val token = result.await()
                authSDK.signUpGoogle(token.idToken.orEmpty())
                    .collect {
                        when (it) {
                            is Response.Error -> cb(false, it.message)
                            Response.Loading -> Unit
                            is Response.Result -> cb(true, "")
                        }
                    }
            } else {
                cb(false, "")
            }

        }
    }
}