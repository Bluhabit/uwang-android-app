/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.dashboard

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.bluehabit.budgetku.feature.dashboard.budget.BudgetViewModel
import com.bluehabit.budgetku.feature.dashboard.budget.ScreenBudget
import com.bluehabit.budgetku.feature.dashboard.community.CommunityViewModel
import com.bluehabit.budgetku.feature.dashboard.community.ScreenCommunity
import com.bluehabit.budgetku.feature.dashboard.home.HomeViewModel
import com.bluehabit.budgetku.feature.dashboard.home.ScreenHome
import com.bluehabit.budgetku.feature.dashboard.report.ReportViewModel
import com.bluehabit.budgetku.feature.dashboard.report.ScreenReport
import com.bluehabit.core.ui.UIController
import com.bluehabit.core.ui.UIListenerData
import com.bluehabit.core.ui.pageWrapper
import com.bluehabit.core.ui.routes.DashboardConstants
import com.bluehabit.core.ui.routes.Routes.Budget
import com.bluehabit.core.ui.routes.Routes.Community
import com.bluehabit.core.ui.routes.Routes.Home
import com.bluehabit.core.ui.routes.Routes.Report

fun NavGraphBuilder.dashboardRoute(
    uiController: UIController
) {

    navigation(
        route = DashboardConstants.parentRoute,
        startDestination = Home.routeName
    ) {

        pageWrapper<HomeViewModel>(
            route = Home.routeName,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            val data by uiDataState.collectAsState()
            ScreenHome(
                state=state,
                data=data,
                invoker = UIListenerData(
                    controller = uiController,
                    state = state,
                    data = data,
                    commit = ::commit,
                    commitData = ::commitData,
                    dispatcher = ::dispatch
                )
            )
        }
        pageWrapper<CommunityViewModel>(
            route = Community.routeName,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            val data by uiDataState.collectAsState()
            ScreenCommunity(
                state=state,
                data=data,
                invoker = UIListenerData(
                    controller = uiController,
                    state = state,
                    data = data,
                    commit = ::commit,
                    commitData = ::commitData,
                    dispatcher = ::dispatch
                )
            )
        }
        pageWrapper<BudgetViewModel>(
            route = Budget.routeName,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            val data by uiDataState.collectAsState()
            ScreenBudget(
                state=state,
                data=data,
                invoker = UIListenerData(
                    controller = uiController,
                    state = state,
                    data = data,
                    commit = ::commit,
                    commitData = ::commitData,
                    dispatcher = ::dispatch
                )
            )
        }
        pageWrapper<ReportViewModel>(
            route = Report.routeName,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            val data by uiDataState.collectAsState()
            ScreenReport(
                state=state,
                data=data,
                invoker = UIListenerData(
                    controller = uiController,
                    state = state,
                    data = data,
                    commit = ::commit,
                    commitData = ::commitData,
                    dispatcher = ::dispatch
                )
            )
        }
    }
}