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
import com.bluehabit.budgetku.feature.budget.createTransaction.CreateTransactionViewModel
import com.bluehabit.budgetku.feature.budget.createTransaction.ScreenCreateTransaction
import com.bluehabit.budgetku.feature.budget.detailTransaction.DetailTransactionViewModel
import com.bluehabit.budgetku.feature.budget.detailTransaction.ScreenDetailTransaction
import com.bluehabit.budgetku.feature.budget.editTransaction.EditTransactionViewModel
import com.bluehabit.budgetku.feature.budget.editTransaction.ScreenEditTransaction
import com.bluehabit.budgetku.feature.budget.listTransaction.ListTransactionViewModel
import com.bluehabit.budgetku.feature.budget.listTransaction.ScreenListTransaction
import com.bluehabit.core.ui.UIController
import com.bluehabit.core.ui.UIListenerData
import com.bluehabit.core.ui.pageWrapper
import com.bluehabit.core.ui.routes.Routes.CreateTransaction
import com.bluehabit.core.ui.routes.Routes.DetailTransaction
import com.bluehabit.core.ui.routes.Routes.EditTransaction
import com.bluehabit.core.ui.routes.Routes.ListTransaction

fun NavGraphBuilder.transactionRoute(
    uiController: UIController
) {

    pageWrapper<ListTransactionViewModel>(
        route = ListTransaction.routeName,
        controller = uiController
    ) {
        val state by uiState.collectAsState()
        val data by uiDataState.collectAsState()
        ScreenListTransaction(
            state = state,
            data = data,
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

    pageWrapper<EditTransactionViewModel>(
        route = EditTransaction.routeName(),
        arguments = EditTransaction.navArgs,
        controller = uiController
    ) {
        val state by uiState.collectAsState()
        val data by uiDataState.collectAsState()
        ScreenEditTransaction(
            state = state,
            data = data,
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

    pageWrapper<DetailTransactionViewModel>(
        route = DetailTransaction.routeName(),
        arguments = DetailTransaction.navArgs,
        controller = uiController
    ) {
        val state by uiState.collectAsState()
        val data by uiDataState.collectAsState()
        ScreenDetailTransaction(
            state = state,
            data = data,
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

    pageWrapper<CreateTransactionViewModel>(
        route = CreateTransaction.routeName,
        controller = uiController
    ) {
        val state by uiState.collectAsState()
        val data by uiDataState.collectAsState()
        ScreenCreateTransaction(
            state = state,
            data = data,
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