/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.bluehabit.budgetku.android.feature.auth.completeProfile.CompleteProfile
import com.bluehabit.budgetku.android.feature.auth.completeProfile.routeCompleteProfile
import com.bluehabit.budgetku.android.feature.auth.signIn.routeSignIn
import com.bluehabit.budgetku.android.feature.auth.signUp.routeSignUp
import com.bluehabit.budgetku.android.feature.dashboard.home.routeHome
import com.bluehabit.budgetku.android.feature.dashboard.profile.routeProfile
import com.bluehabit.budgetku.android.feature.onboarding.routeOnboard
import com.bluehabit.budgetku.android.feature.splashScreen.routeSplash

@Composable
fun AppNavigation(
    applicationState: ApplicationState
) {
    NavHost(
        navController = applicationState.router,
        startDestination = CompleteProfile.routeName
    ) {
        routeSplash(
            state = applicationState
        )
        routeOnboard(
            state = applicationState
        )
        routeSignIn(
            state = applicationState
        )
        routeSignUp(
            state = applicationState
        )
        routeCompleteProfile(
            state = applicationState
        )
        routeHome(
            state = applicationState
        )
        routeProfile(
            state = applicationState
        )


    }
}