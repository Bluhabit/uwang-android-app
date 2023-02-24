package com.bluehabit.budgetku.android.feature.signIn

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.EventListener

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

        ScreenSignIn(
            userID = userId,
            onSubmit = { email, password ->

                viewModel.signInWithEmail(email, password)
            }
        )
    }
}