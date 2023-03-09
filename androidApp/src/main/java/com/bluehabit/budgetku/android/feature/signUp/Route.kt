/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.signUp

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.contract.GoogleAuthContract
import com.bluehabit.budgetku.android.base.extensions.navigateAndReplaceAll
import com.bluehabit.budgetku.android.feature.signIn.SignIn

object SignUp {
    const val routeName = "SignUp"
}

fun NavGraphBuilder.routeSignUp(
    state: ApplicationState,
) {
    composable(SignUp.routeName) {
        val viewModel = hiltViewModel<SignUpViewModel>()


        val launcher = rememberLauncherForActivityResult(contract = GoogleAuthContract(), onResult = {
            viewModel.signUpGoogle(it) { success, message ->

            }

        })

        ScreenSignUp(
            onSubmit = { email, password, name ->
                viewModel.signUpWithEmail(name, email, password) { success, message ->

                }
            },
            onSubmitWithGoogle = {
                launcher.launch(1)
            },
            onSignIn = {
                state.navigateAndReplaceAll(SignIn.routeName)
            }
        )

    }
}