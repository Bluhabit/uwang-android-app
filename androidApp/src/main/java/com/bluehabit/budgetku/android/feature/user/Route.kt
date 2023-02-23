package com.bluehabit.budgetku.android.feature.user

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.EventListener

object User {
    const val routeName = "User"
}

fun NavGraphBuilder.routeUser(
    state: ApplicationState,
    event: EventListener
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