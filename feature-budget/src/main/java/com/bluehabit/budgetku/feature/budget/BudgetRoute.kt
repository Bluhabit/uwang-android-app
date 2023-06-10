/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import com.bluehabit.budgetku.feature.budget.createAccount.CreateAccountViewModel
import com.bluehabit.budgetku.feature.budget.createAccount.ScreenCreateAccount
import com.bluehabit.budgetku.feature.budget.createAccountSaving.CreateAccountSavingViewModel
import com.bluehabit.budgetku.feature.budget.createAccountSaving.ScreenCreateAccountSaving
import com.bluehabit.budgetku.feature.budget.createBudget.CreateBudgetViewModel
import com.bluehabit.budgetku.feature.budget.createBudget.ScreenCreateBudget
import com.bluehabit.budgetku.feature.budget.listAccount.ListAccountViewModel
import com.bluehabit.budgetku.feature.budget.listAccount.ScreenListAccount
import com.bluehabit.budgetku.feature.budget.resultCreateBudget.ResultCreateBudgetViewModel
import com.bluehabit.budgetku.feature.budget.resultCreateBudget.ScreenResultCreateBudget
import com.bluehabit.budgetku.feature.budget.tutorialBudget.ScreenTutorialBudget
import com.bluehabit.budgetku.feature.budget.tutorialBudget.TutorialBudgetViewModel
import com.bluehabit.core.ui.UIController
import com.bluehabit.core.ui.UIListener
import com.bluehabit.core.ui.UIListenerData
import com.bluehabit.core.ui.pageWrapper
import com.bluehabit.core.ui.routes.Routes.CreateAccountSaving
import com.bluehabit.core.ui.routes.Routes.CreateBudget
import com.bluehabit.core.ui.routes.Routes.ListAccount
import com.bluehabit.core.ui.routes.Routes.ResultCreateBudget
import com.bluehabit.core.ui.routes.Routes.TutorialBudget

fun NavGraphBuilder.budgetRoute(
    uiController: UIController
) {
    pageWrapper<TutorialBudgetViewModel>(
        route = TutorialBudget.routeName,
        controller = uiController
    ) {
        val state by uiState.collectAsState()
        ScreenTutorialBudget(
            state = state,
            invoker = UIListener(
                controller = uiController,
                state = state,
                commit = ::commit,
                dispatcher = ::dispatch
            )
        )
    }

    pageWrapper<ResultCreateBudgetViewModel>(
        route = ResultCreateBudget.routeName,
        controller = uiController
    ) {
        val state by uiState.collectAsState()
        val data by uiDataState.collectAsState()
        ScreenResultCreateBudget(
            state = state,
            data = data,
            invoker = UIListenerData(
                controller = uiController,
                state = state,
                data = data,
                commitData = ::commitData,
                commit = ::commit,
                dispatcher = ::dispatch
            )
        )
    }

    pageWrapper<ListAccountViewModel>(
        route = ListAccount.routeName,
        controller = uiController
    ) {
        val state by uiState.collectAsState()
        val data by uiDataState.collectAsState()
        ScreenListAccount(
            state = state,
            data = data,
            invoker = UIListenerData(
                controller = uiController,
                state = state,
                data = data,
                commitData = ::commitData,
                commit = ::commit,
                dispatcher = ::dispatch
            )
        )
    }
    pageWrapper<CreateBudgetViewModel>(
        route = CreateBudget.routeName,
        controller = uiController
    ) {
        val state by uiState.collectAsState()
        val data by uiDataState.collectAsState()
        ScreenCreateBudget(
            state = state,
            data = data,
            invoker = UIListenerData(
                controller = uiController,
                state = state,
                data = data,
                commitData = ::commitData,
                commit = ::commit,
                dispatcher = ::dispatch
            )
        )
    }
    pageWrapper<CreateAccountSavingViewModel>(
        route = CreateAccountSaving.routeName,
        controller = uiController
    ) {
        val state by uiState.collectAsState()
        val data by uiDataState.collectAsState()
        ScreenCreateAccountSaving(
            state = state,
            data = data,
            invoker = UIListenerData(
                controller = uiController,
                state = state,
                data = data,
                commitData = ::commitData,
                commit = ::commit,
                dispatcher = ::dispatch
            )
        )
    }
    pageWrapper<CreateAccountViewModel>(
        route = CreateAccountSaving.routeName,
        controller = uiController
    ) {
        val state by uiState.collectAsState()
        val data by uiDataState.collectAsState()
        ScreenCreateAccount(
            state = state,
            data = data,
            invoker = UIListenerData(
                controller = uiController,
                state = state,
                data = data,
                commitData = ::commitData,
                commit = ::commit,
                dispatcher = ::dispatch
            )
        )
    }
}