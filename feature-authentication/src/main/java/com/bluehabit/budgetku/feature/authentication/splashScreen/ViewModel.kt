/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.splashScreen

import com.bluehabit.budgetku.android.feature.onboarding.Onboard
import com.bluehabit.budgetku.data.domain.auth.CheckSessionUseCase
import com.bluehabit.core.ui.extensions.navigateAndReplaceAll
import com.bluehabit.core.ui.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkSessionUseCase: CheckSessionUseCase
) : BaseViewModel<SplashState, SplashEvent>(SplashState()) {

    init {
        handleActions()
    }

    private fun checkIfUserLoggedIn() = async {
//        if (checkSessionUseCase()) navigateAndReplaceAll(Home.routeName)
//        else
            controller.navigateAndReplaceAll(Onboard.routeName)
    }


    override fun handleActions() = onEvent {
        when (it) {
            SplashEvent.CheckSession -> checkIfUserLoggedIn()
        }
    }
}