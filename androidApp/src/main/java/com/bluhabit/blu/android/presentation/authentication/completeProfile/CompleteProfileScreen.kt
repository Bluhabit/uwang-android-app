/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.completeProfile

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.presentation.authentication.completeProfile.screen.InputDobScreen
import com.bluhabit.blu.android.presentation.authentication.completeProfile.screen.SetPreferenceScreen
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.components.dialog.DialogPrimary
import com.bluhabit.core.ui.theme.UwangColors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun CompleteProfileScreen(
    navHostController: NavHostController = rememberNavController(),
    stateFlow: Flow<CompleteProfileState> = flowOf(),
    effectFlow: Flow<CompleteProfileEffect> = flowOf(),
    onAction: (CompleteProfileAction) -> Unit = {},
) {
    val context = LocalContext.current
    val state by stateFlow.collectAsStateWithLifecycle(initialValue = CompleteProfileState())
    val effect by effectFlow.collectAsState(initial = CompleteProfileEffect.None)
    LaunchedEffect(key1 = effect, block = {
        when (val currentEffect = effect) {
            CompleteProfileEffect.None -> Unit
            is CompleteProfileEffect.Error -> {
                Toast.makeText(context, currentEffect.errorMessage, Toast.LENGTH_LONG).show()
            }
        }
    })

    if (state.showDialogSuccess) {
        DialogPrimary(
            show = true,
            title = stringResource(id = R.string.text_title_success_profile),
            message = stringResource(id = R.string.text_subtitle_success_profile),
            image = {
                Image(
                    painter = painterResource(id = R.drawable.success),
                    contentDescription = "",
                )
            },
            dismissIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    tint = UwangColors.Neutral.Grey12,
                    contentDescription = "",
                    modifier = Modifier
                        .clickable {
                            // On Dismiss
                        }
                )
            },
            action = {
                ButtonPrimary(
                    text = stringResource(id = R.string.text_btn_success_profile),
                    onClick = {
                        // On Action
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        )
    }


    fun goBack() {
        if (state.currentScreen > 0) {
            onAction(
                CompleteProfileAction.OnScreenChange(
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
        0 -> InputDobScreen(
            state = state,
            onBackPressed = {},
            onAction = onAction
        )

        1 -> SetPreferenceScreen(
            state = state,
            onBackPressed = {
                goBack()
            },
            onAction = onAction
        )
    }
}