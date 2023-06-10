/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.bluehabit.core.ui.routes.AuthenticationConstants
import com.bluehabit.budgetku.feature.authentication.authenticationRoute
import com.bluehabit.budgetku.feature.budget.budgetRoute
import com.bluehabit.budgetku.feature.budget.communityRoute
import com.bluehabit.budgetku.feature.budget.profileRoute
import com.bluehabit.budgetku.feature.budget.transactionRoute
import com.bluehabit.budgetku.feature.dashboard.dashboardRoute
import com.bluehabit.core.ui.UIController

@Composable
fun AppNavigation(
    uiController: UIController
) {
    NavHost(
        navController = uiController.router,
        startDestination = AuthenticationConstants.parentRoute
    ) {
        authenticationRoute(uiController = uiController)
        dashboardRoute(uiController = uiController)
        profileRoute(uiController = uiController)
        communityRoute(uiController = uiController)
        budgetRoute(uiController = uiController)
        transactionRoute(uiController = uiController)
    }
}