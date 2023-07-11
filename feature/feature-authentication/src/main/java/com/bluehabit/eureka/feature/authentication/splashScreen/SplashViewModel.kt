/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.splashScreen

import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.eureka.data.domain.auth.CheckSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkSessionUseCase: CheckSessionUseCase
) : MviViewModel<SplashState,SplashIntent, SplashAction>(SplashState()) {

    private fun checkIfUserLoggedIn() = async {
//        if (checkSessionUseCase()) navigateAndReplaceAll(Home.routeName)
//        else
           // controller.navigateAndReplace(Routes.Onboard.routeName)
    }

    override fun onAction(action: SplashAction) {
        when (action) {
            SplashAction.CheckSession -> checkIfUserLoggedIn()
        }
    }
}