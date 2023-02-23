package com.bluehabit.budgetku.android.feature.user

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

object User {
    const val routeName = "User"
}

fun NavGraphBuilder.routeUser(
    router: NavHostController
) {
    composable(User.routeName) {
        val viewModel = hiltViewModel<UserViewModel>()
        val userId by viewModel.userData.collectAsState()

        ScreenUser(
            userID = userId,
            onSubmit = { email, password ->
                viewModel.signInWithEmail(email, password)
            }
        )
    }
}