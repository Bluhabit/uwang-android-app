/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android

import androidx.compose.runtime.Composable
import app.trian.mvi.Navigation
import app.trian.mvi.ui.internal.UIContract
import com.bluehabit.budgetku.feature.authentication.splashScreen.SplashAction
import com.bluehabit.budgetku.feature.authentication.splashScreen.SplashIntent
import com.bluehabit.budgetku.feature.authentication.splashScreen.SplashState
import com.bluehabit.budgetku.feature.authentication.splashScreen.SplashViewModel

@Navigation(
    route = "HEHEH",
    viewModel = SplashViewModel::class
)
@Composable
fun ScreenTest(
    uiContract: UIContract<SplashState,SplashIntent,SplashAction>
) {

}