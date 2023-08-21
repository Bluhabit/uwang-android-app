/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.contract.UIContract
import app.trian.mvi.ui.internal.rememberUIController
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.eureka.feature.authentication.onboarding.screen.ScreenLoading
import com.bluehabit.eureka.feature.authentication.onboarding.screen.ScreenOnboarding

@Navigation(
    route = Routes.Onboard.routeName,
    viewModel = OnboardingViewModel::class
)
@Composable
fun OnboardingScreen(
    uiContract: UIContract<OnboardingState, OnboardingAction>,
    modifier: Modifier = Modifier
) = UIWrapper(uiContract = uiContract) {

    UseEffect<OnboardingEffect>(commit = { copy(effect = OnboardingEffect.Nothing) }, onEffect = {
        when (this) {
            OnboardingEffect.NavigateToHome -> {
                navigator.navigateAndReplace(Routes.Dashboard.routeName)
            }

            OnboardingEffect.Nothing -> Unit
        }
    })

    LaunchedEffect(key1 = Unit, block = {
        dispatch(OnboardingAction.CheckSession)
    })

    if (state.isLoading) {
        ScreenLoading()
    } else {
        ScreenOnboarding(
            navigateToAuth = {
                navigator.navigateAndReplace(Routes.Auth.routeName)
            }
        )
    }


}

@Preview
@Composable
fun PreviewOnboarding() {
    GaweanTheme {
        OnboardingScreen(
            UIContract(
                rememberUIController(),
                OnboardingState()
            )
        )
    }
}