/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.splashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
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
) = UIWrapper<SplashViewModel>(appState = state) {
    LaunchedEffect(key1 = this, block = {
        dispatch(SplashEvent.CheckSession)
    })
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
       Image(
           painter = painterResource(id = R.drawable.logo_budgetku),
           contentDescription = "Logo App"
       )
    }
}


@Preview
@Composable
fun PreviewScreenSplash() {
    BaseMainApp {
        ScreenSplash(rememberApplicationState())
    }
}