/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.signIn

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.contract.GoogleAuthContract
import com.bluehabit.budgetku.android.base.extensions.navigateSingleTop
import com.bluehabit.budgetku.android.feature.signUp.SignUp

object SignIn {
    const val routeName = "SignIn"
}

fun NavGraphBuilder.routeSignIn(
    state: ApplicationState,
) {
    composable(SignIn.routeName) {
        val viewModel = hiltViewModel<UserViewModel>()

        val launcher = rememberLauncherForActivityResult(contract = GoogleAuthContract(), onResult = {
            viewModel.signInGoogle(it){ success,message->

            }
        })

        ScreenSignIn(
            onSubmit = { email, password ->
                viewModel.signInWithEmail(email,password){
                    success,message->
                }

            },
            onSubmitWithGoogle = {
                launcher.launch(1)
            },
            onSignUp = {
                state.navigateSingleTop(SignUp.routeName)
            }
        )
    }
}

