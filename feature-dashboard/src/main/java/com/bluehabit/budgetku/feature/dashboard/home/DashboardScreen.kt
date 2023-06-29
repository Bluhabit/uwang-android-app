/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.dashboard.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.UIContract
import com.bluehabit.budgetku.feature.dashboard.component.DashboardBottomNavigation
import com.bluehabit.budgetku.feature.dashboard.home.components.ScreenBudget
import com.bluehabit.budgetku.feature.dashboard.home.components.ScreenCommunity
import com.bluehabit.budgetku.feature.dashboard.home.components.ScreenHome
import com.bluehabit.budgetku.feature.dashboard.home.components.ScreenReport
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.BaseScreen
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetAddBudgetTransaction
import com.bluehabit.core.ui.routes.Routes

@Composable
fun DashboardScreen(
    uiContract: UIContract<DashboardState, DashboardIntent, DashboardAction>
) = UIWrapper(
    uiContract
) {
    BaseScreen(
        bottomSheet = {
            BottomSheetAddBudgetTransaction(
                onDismiss = {
                    //  hideBottomSheet()
                },
                onAddAccount = {
                    //  hideBottomSheet()
                    //  navigateSingleTop(CreateAccount.routeName)
                },
                onAddTransaction = {
                    //  hideBottomSheet()
                    //navigateSingleTop(CreateTransaction.routeName)
                },
                onAddBudget = {
                    // hideBottomSheet()
                    //navigateSingleTop(CreateBudget.routeName)
                },
                onAddTransfer = {},
            )
        },
        bottomBar = {
            DashboardBottomNavigation(
                currentRoute = "",
                onRefresh = {},
                onClick = {}
            )
        }
    ) {
        when (state.currentScreen) {
            Routes.Home.routeName -> ScreenHome(
                state = state,
                onShowBalance = {
                    commit { copy(showBalance = it) }
                },
                onNavigate = {
                    navigator.navigate(it)
                },
                onNavigateSingleTop = { route, params ->
                    navigator.navigateSingleTop(route, *params)
                }
            )

            Routes.Report.routeName -> ScreenReport(
                state=state
            )
            Routes.Community.routeName-> ScreenCommunity(
                state=state,
                onSelectCategory = {
                    commit { copy(selectedCategory = it) }
                }
            )
            Routes.Budget.routeName-> ScreenBudget(
                state=state,
                onChangeHasBudget = {
                    commit { copy(hasBudget = it) }
                },
                onShowBottomSheet = {
                    commit {
                        copy(bottomSheetType = it)
                    }
                   // showBottomSheet()
                },
                onNavigateSingleTop = {
                    navigator.navigateSingleTop(it)
                }
            )
        }
    }
}


@Preview
@Composable
fun PreviewDashboardScreen() {
    BaseMainApp() {
        DashboardScreen(
            UIContract(
                controller = it,
                state = DashboardState()
            )
        )
    }

}