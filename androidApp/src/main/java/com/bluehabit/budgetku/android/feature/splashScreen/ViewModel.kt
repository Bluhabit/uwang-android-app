/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.splashScreen

import com.bluehabit.budgetku.android.base.BaseViewModel
import com.bluehabit.budgetku.android.feature.dashboard.home.Home
import com.bluehabit.budgetku.android.feature.onboarding.Onboard
import com.bluehabit.budgetku.data.domain.auth.CheckSessionUseCase
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
        if (checkSessionUseCase()) navigateAndReplaceAll(Home.routeName)
        else navigateAndReplaceAll(Onboard.routeName)
    }


    override fun handleActions() = onEvent {
        when (it) {
            SplashEvent.CheckSession -> checkIfUserLoggedIn()
        }
    }
}