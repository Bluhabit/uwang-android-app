/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.onboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bluhabit.blu.android.MainActivity
import com.bluhabit.blu.android.common.findActivity
import kotlinx.coroutines.flow.Flow

@Composable
fun OnboardScreen(
    stateFlow: Flow<OnboardState>,
    effectFlow: Flow<OnboardEffect>,
    onAction: (OnboardAction) -> Unit
) {

    val state by stateFlow.collectAsStateWithLifecycle(initialValue = OnboardState())
    val effect by effectFlow.collectAsStateWithLifecycle(initialValue = OnboardEffect.None)
    val ctx = LocalContext.current
    val activity = ctx.findActivity()

    LaunchedEffect(key1 = effect, block = {
        when (effect) {
            OnboardEffect.None -> {

            }
        }
    })
    LaunchedEffect(key1 = Unit, block = {
        activity.addEventListener(object :MainActivity.SseListener{
            override fun onEvent() {

            }

        })
    })


}