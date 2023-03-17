/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.signIn

import androidx.lifecycle.viewModelScope
import com.bluehabit.budgetku.android.base.BaseViewModel
import com.bluehabit.budgetku.android.base.extensions.navigateAndReplaceAll
import com.bluehabit.budgetku.android.feature.dashboard.home.Home
import com.bluehabit.budgetku.sdk.auth.AuthSDK
import com.bluehabit.budgetku.sdk.user.UserSDK
import com.bluehabit.budgetku.utils.Response
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authSDK: AuthSDK,
    private val userSDK: UserSDK
) : BaseViewModel<SignInState>(SignInState()) {

    fun setEmail(email: String) {
        _uiState.tryEmit(
            uiState.value.copy(
                email = email
            )
        )
    }

    fun setPassword(password: String) {
        _uiState.tryEmit(
            uiState.value.copy(
                password = password
            )
        )
    }
    fun signInWithEmail() = with(viewModelScope) {
        launch {
            authSDK.signInWithEmail(uiState.value.email, uiState.value.password)
                .collect {
                    when (it) {
                        is Response.Error -> {
                            app.showSnackbar(it.message)
                        }
                        Response.Loading -> Unit
                        is Response.Result -> {
                            app.navigateAndReplaceAll(
                                Home.routeName
                            )
                        }
                    }
                }
        }
    }

    fun signInGoogle(
        result: Task<GoogleSignInAccount>?,
    ) = with(viewModelScope) {
        launch {
            if (result != null) {
                val token = result.await()
                authSDK.signInGoogle(token.idToken.orEmpty())
                    .collect {
                        when (it) {
                            is Response.Error -> {
                                app.showSnackbar(it.message)
                            }
                            Response.Loading -> Unit
                            is Response.Result -> {
                                app.navigateAndReplaceAll(
                                    Home.routeName
                                )
                            }
                        }
                    }
            } else {
                app.showSnackbar("Sign in canceled by provider")
            }

        }
    }



}