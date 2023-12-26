/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.completeProfile

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bluhabit.blu.android.presentation.authentication.completeProfile.screen.InputDobScreen
import com.bluhabit.blu.android.presentation.authentication.completeProfile.screen.InputUsernameScreen
import com.bluhabit.blu.android.presentation.authentication.completeProfile.screen.SetPreferenceScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun CompleteProfileScreen(
    navHostController: NavHostController = rememberNavController(),
    stateFlow: Flow<CompleteProfileState> = flowOf(),
    effectFlow: Flow<CompleteProfileEffect> = flowOf(),
    onAction: (CompleteProfileAction) -> Unit = {},
) {
    val state by stateFlow.collectAsStateWithLifecycle(initialValue = CompleteProfileState())
    val effect by effectFlow.collectAsState(initial = CompleteProfileEffect.None)
    LaunchedEffect(key1 = effect, block = {
        when (effect) {
            CompleteProfileEffect.None -> Unit
        }
    })

    fun goBack() {
        if(state.currentScreen > 0){
            onAction(CompleteProfileAction.OnScreenChange(
                screen= state.currentScreen -1
            ))
        }else{
            navHostController.navigateUp()
        }
    }
    BackHandler {
        goBack()
    }

    when (state.currentScreen) {
        0 -> InputDobScreen(
            state=state,
            onBackPressed = {},
            onAction=onAction
        )
        1 -> SetPreferenceScreen(
            state=state,
            onBackPressed = {
                goBack()
            },
            onAction=onAction
        )
        2 -> InputUsernameScreen(
            state=state,
            onBackPressed = {
                goBack()
            },
            onAction = onAction
        )
    }
}