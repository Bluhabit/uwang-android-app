/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.bluehabit.budgetku.android.feature.auth.checkEmailResetPassword.routeCheckEmailResetPassword
import com.bluehabit.budgetku.android.feature.auth.changePassword.routeChangePassword
import com.bluehabit.budgetku.android.feature.auth.completeProfile.routeCompleteProfile
import com.bluehabit.budgetku.android.feature.auth.inputPin.routeInputPin
import com.bluehabit.budgetku.android.feature.auth.createNewPassword.routeCreateNewPassword
import com.bluehabit.budgetku.android.feature.auth.resetPassword.routeResetPassword
import com.bluehabit.budgetku.android.feature.auth.signIn.routeSignIn
import com.bluehabit.budgetku.android.feature.auth.signUp.routeSignUp
import com.bluehabit.budgetku.android.feature.createAccount.routeCreateAccount
import com.bluehabit.budgetku.android.feature.createBudget.routeCreateBudget
import com.bluehabit.budgetku.android.feature.createTransaction.routeCreateTransaction
import com.bluehabit.budgetku.android.feature.dashboard.budget.routeBudget
import com.bluehabit.budgetku.android.feature.dashboard.community.routeCommunity
import com.bluehabit.budgetku.android.feature.dashboard.home.routeHome
import com.bluehabit.budgetku.android.feature.dashboard.report.routeReport
import com.bluehabit.budgetku.android.feature.listAccount.routeListAccount
import com.bluehabit.budgetku.android.feature.onboarding.routeOnboard
import com.bluehabit.budgetku.android.feature.resultCreateBudget.routeResultCreateBudget
import com.bluehabit.budgetku.android.feature.splashScreen.Splash
import com.bluehabit.budgetku.android.feature.splashScreen.routeSplash

@Composable
fun AppNavigation(
    applicationState: ApplicationState
) {
    NavHost(
        navController = applicationState.router,
        startDestination = Splash.routeName
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
        routeCommunity(
            state = applicationState
        )
        routeBudget(
            state = applicationState
        )
        routeReport(
            state = applicationState
        )
        routeCreateBudget(
            state = applicationState
        )
        routeInputPin(
            state = applicationState
        )
        routeChangePassword(
            state = applicationState
        )
        routeResultCreateBudget(
            state = applicationState
        )

        routeResetPassword(
            state = applicationState
        )
        routeCreateNewPassword(
            state = applicationState
        )
        routeCheckEmailResetPassword(
            state = applicationState
        )
        routeCreateTransaction(
            state = applicationState
        )
        routeCreateAccount(
            state = applicationState
        )
        routeListAccount(
            state = applicationState
        )
    }
}