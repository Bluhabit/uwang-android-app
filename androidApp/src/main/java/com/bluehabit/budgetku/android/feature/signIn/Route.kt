package com.bluehabit.budgetku.android.feature.signIn

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.EventListener
import com.bluehabit.budgetku.android.base.contract.GoogleAuthContract

object SignIn {
    const val routeName = "SignIn"
}

fun NavGraphBuilder.routeSignIn(
    state: ApplicationState,
    event: EventListener
) {
    composable(SignIn.routeName) {
        val viewModel = hiltViewModel<UserViewModel>()
        val userId by viewModel.userData.collectAsState()

        val launcher = rememberLauncherForActivityResult(contract = GoogleAuthContract(), onResult = {
            viewModel.signInGoogle(it)
        })

        ScreenSignIn(
            userID = userId,
            onSubmit = { email, password ->
                launcher.launch(1)

                //viewModel.signInWithEmail(email, password)
            }
        )
    }
}