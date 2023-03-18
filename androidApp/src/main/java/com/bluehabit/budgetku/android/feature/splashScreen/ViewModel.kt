/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.splashScreen

import com.bluehabit.budgetku.android.base.BaseViewModel
import com.bluehabit.budgetku.android.base.extensions.navigateAndReplaceAll
import com.bluehabit.budgetku.android.feature.dashboard.home.Home
import com.bluehabit.budgetku.android.feature.signIn.SignIn
import com.bluehabit.budgetku.sdk.auth.AuthSDK
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authSDK: AuthSDK
) : BaseViewModel<String, SplashEvent>("") {

    init {
        handleEvent()
    }

    private fun checkIfUserLoggedIn() = async {
//        app.navigateSingleTop(Home.routeName)
            if (authSDK.isLoggedIn()) {
                app.navigateAndReplaceAll(Home.routeName)
            } else {
                app.navigateAndReplaceAll(SignIn.routeName)
            }
    }


    override fun handleEvent() = onEvent {
        when (it) {
            SplashEvent.CheckSession -> {
                checkIfUserLoggedIn()
            }
            SplashEvent.Init -> Unit
        }
    }
}