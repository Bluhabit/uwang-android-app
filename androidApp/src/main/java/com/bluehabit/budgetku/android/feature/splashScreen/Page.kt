/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.splashScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.feature.signIn.SignInEvent
import com.bluehabit.budgetku.android.rememberApplicationState

object Splash {
    const val routeName = "Splash"
}

fun NavGraphBuilder.routeSplash(
    state: ApplicationState,
) {
    composable(Splash.routeName) {
        ScreenSplash(state = state)

    }
}

@Composable
internal fun ScreenSplash(
    state: ApplicationState,
) {
    UIWrapper<String, SplashEvent, SplashViewModel>(appState = state) {
        LaunchedEffect(key1 = this, block = {
            sendEvent(SplashEvent.CheckSession)
        })
        Column {

        }
    }
}

@Preview
@Composable
fun PreviewScreenSplash() {
    BaseMainApp {
        ScreenSplash(rememberApplicationState())
    }
}