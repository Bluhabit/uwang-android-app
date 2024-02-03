/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.personalization

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bluhabit.blu.android.presentation.authentication.personalization.screen.ChooseLevelScreen
import com.bluhabit.blu.android.presentation.authentication.personalization.screen.ChooseTopicScreen
import com.bluhabit.blu.android.presentation.authentication.personalization.screen.CreateUsernameScreen
import com.bluhabit.blu.android.presentation.authentication.personalization.screen.UploadPhotoProfileScreen
import com.bluhabit.core.ui.components.dialog.DialogLoading
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun CompleteProfileScreen(
    navHostController: NavHostController = rememberNavController(),
    stateFlow: Flow<PersonalizationState> = flowOf(PersonalizationState()),
    effectFlow: Flow<PersonalizationEffect> = flowOf(),
    onAction: (PersonalizationAction) -> Unit = {},
) {
    val context = LocalContext.current
    val effect by effectFlow.collectAsState(initial = PersonalizationEffect.None)
    val state by stateFlow.collectAsStateWithLifecycle(initialValue = PersonalizationState())
    DialogLoading(show = state.showLoading)

    fun goBack() {
        if (state.currentScreen > 0) {
            onAction(
                PersonalizationAction.OnScreenChange(
                    screen = state.currentScreen - 1
                )
            )
        } else {
            navHostController.navigateUp()
        }
    }
    BackHandler {
        goBack()
    }

    when (state.currentScreen) {
        0 -> CreateUsernameScreen(
            state = state,
            onAction = onAction
        )

        1 -> UploadPhotoProfileScreen(
            state = state,
            onAction = onAction
        )

        2 -> ChooseTopicScreen(
            state = state,
            onAction = onAction
        )

        3 -> ChooseLevelScreen(
            state = state,
            onAction = onAction
        )
    }
}