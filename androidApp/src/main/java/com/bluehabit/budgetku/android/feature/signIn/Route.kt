/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.signIn

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.contract.GoogleAuthContract
import com.bluehabit.budgetku.android.base.extensions.navigateAndReplaceAll
import com.bluehabit.budgetku.android.base.extensions.showSnackbar
import com.bluehabit.budgetku.android.feature.dashboard.home.Home

object SignIn {
    const val routeName = "SignIn"
}

fun NavGraphBuilder.routeSignIn(
    state: ApplicationState,
) {
    composable(SignIn.routeName) {
        val viewModel = hiltViewModel<UserViewModel>()
        val userId by viewModel.userData.collectAsState()

        val launcher = rememberLauncherForActivityResult(contract = GoogleAuthContract(), onResult = {
            viewModel.signInGoogle(it){
                success,message->
                state.showSnackbar(message)
            }
        })

        ScreenSignIn(
            userID = userId,
            onSubmit = { email, password ->
                //launcher.launch(1)

                state.navigateAndReplaceAll(Home.routeName)
            }
        )
    }
}