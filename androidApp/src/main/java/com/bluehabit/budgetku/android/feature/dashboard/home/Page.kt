/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.base.extensions.bottomNavigationListener
import com.bluehabit.budgetku.android.base.extensions.formatToRupiah
import com.bluehabit.budgetku.android.base.extensions.gridItems
import com.bluehabit.budgetku.android.base.extensions.navigateSingleTop
import com.bluehabit.budgetku.android.base.listener.BottomNavigationListener
import com.bluehabit.budgetku.android.components.bottomSheet.BottomSheetAddBudgetTransaction
import com.bluehabit.budgetku.android.components.ItemArticleGrid
import com.bluehabit.budgetku.android.components.CardChallengeBudgeting
import com.bluehabit.budgetku.android.components.CardMonthlyBudget
import com.bluehabit.budgetku.android.components.DashboardBottomNavigation
import com.bluehabit.budgetku.android.components.DashboardBottomNavigationMenu
import com.bluehabit.budgetku.android.components.HeaderDashboardHome
import com.bluehabit.budgetku.android.components.HeaderSection
import com.bluehabit.budgetku.android.components.ItemAccount
import com.bluehabit.budgetku.android.components.ItemTransaction
import com.bluehabit.budgetku.android.components.ItemTutorial
import com.bluehabit.budgetku.android.feature.createAccount.CreateAccount
import com.bluehabit.budgetku.android.feature.createBudget.CreateBudget
import com.bluehabit.budgetku.android.feature.createTransaction.CreateTransaction
import com.bluehabit.budgetku.android.feature.dashboard.budget.Budget
import com.bluehabit.budgetku.android.feature.detailTransaction.DetailTransaction
import com.bluehabit.budgetku.android.feature.listAccount.ListAccount
import com.bluehabit.budgetku.android.feature.listTransaction.ListTransaction
import com.bluehabit.budgetku.android.feature.profile.Profile
import com.bluehabit.budgetku.android.rememberApplicationState

object Home {
    const val routeName = "Home"
}

fun NavGraphBuilder.routeHome(
    state: ApplicationState
) {
    composable(Home.routeName) {
        ScreenHome(appState = state)

    }
}

@Composable
internal fun ScreenHome(
    appState: ApplicationState
) = UIWrapper<HomeViewModel>(
    appState = appState
) {
    val state by uiState.collectAsState(initial = HomeState())
    val dataState by uiDataState.collectAsState(initial = HomeDataState())



    with(appState) {
        hideTopAppBar()
        setupBottomSheet {
            BottomSheetAddBudgetTransaction(
                onDismiss = {
                    hideBottomSheet()
                },
                onAddAccount = {
                    hideBottomSheet()
                    navigateSingleTop(CreateAccount.routeName)
                },
                onAddTransaction = {
                    hideBottomSheet()
                    navigateSingleTop(CreateTransaction.routeName)
                },
                onAddBudget = {
                    hideBottomSheet()
                    navigateSingleTop(CreateBudget.routeName)
                },
                onAddTransfer = {},
            )
        }
        bottomNavigationListener(object : BottomNavigationListener {
            override fun onRefresh(item: DashboardBottomNavigationMenu) {
                // remove empty

            }

            override fun onNavigate(item: DashboardBottomNavigationMenu) {
                navigateSingleTop(item.route)
            }

            override fun onFab() {
                showBottomSheet()
            }

        })
    }
    LazyColumn(
        content = {
            item {
                HeaderDashboardHome(
                    displayName = dataState.profile.fullName,
                    currentMonth = dataState.currentMonth,
                    remainingBalance = dataState.remainingBalance,
                    expenses = dataState.expenses,
                    income = dataState.income,
                    showBalance = state.showBalance,
                    onShowBalance = { commit { copy(showBalance = it) } },
                    onShowProfile = {
                        navigateSingleTop(Profile.routeName)
                    }
                )
            }
            item {
                HeaderSection(
                    title = stringResource(R.string.title_section_list_account_dashboard_home),
                    onClick = {
                        navigateSingleTop(ListAccount.routeName)
                    }
                )
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    content = {
                        item {
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                        items(dataState.accounts) {
                            ItemAccount(
                                icon = it.icon,
                                accountBalance = it.accountBalance,
                                accountBankName = it.accountName,
                                onClick = {
                                    navigateSingleTop(ListAccount.routeName)
                                }
                            )
                        }
                    })
            }
            item {
                HeaderSection(
                    title = stringResource(R.string.title_section_monthly_budgeting_dashboard_home),
                    onClick = {
                        navigateSingleTop(Budget.routeName)
                    }
                )
            }
            item {
                CardMonthlyBudget(
                    remainingBudget = dataState.remainingBudget,
                    usedAmount = dataState.usedAmount,
                    totalBudget = dataState.totalBudget,
                    score = dataState.score,
                    transactions = dataState.latestTransaction
                )
            }
            item {
                HeaderSection(
                    title = stringResource(R.string.title_section_new_transaction_dashboard_home),
                    onClick = {
                        navigateSingleTop(ListTransaction.routeName)
                    }
                )
            }
            items(dataState.transactions) {
                ItemTransaction(
                    transactionName = it.transactionName,
                    transactionAccountName = it.transactionAccountName,
                    transactionCategoryName = it.transactionCategory,
                    transactionDate = it.transactionDate,
                    transactionAmount = stringResource(
                        if (it.isTransactionExpenses) R.string.text_expenses else R.string.text_income,
                        it.transactionAmount.formatToRupiah()
                    ),
                    transactionIcon=it.transactionIcon,
                    isExpenses = it.isTransactionExpenses,
                    onClick = {
                        navigateSingleTop(DetailTransaction.routeName)
                    }
                )
            }
            item {
                HeaderSection(
                    title = stringResource(R.string.title_section_challenge_dashboard_home),
                    onClick = {}
                )
            }
            items(dataState.challenge) {
                CardChallengeBudgeting(
                    title = it.title,
                    message = it.message,
                    image = it.image,
                    point = it.totalPoints,
                    pointTarget = it.targetPoints,
                    color = Color(it.color),
                    textColor = Color(it.textColor),
                    progress = it.progress,
                    margin = PaddingValues(
                        horizontal = 20.dp,
                        vertical = 8.dp
                    ),
                    onClick = {}
                )

            }
            item {
                HeaderSection(
                    title = stringResource(R.string.title_section_tutorial_dashboard_home),
                    onClick = {}
                )
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    content = {
                        item {
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                        items(dataState.tutorial) {
                            ItemTutorial(
                                title = it.title,
                                image = it.image
                            )
                        }
                    }
                )
            }
            item {
                HeaderSection(
                    title = stringResource(R.string.title_section_article_dashboard_home)
                ) {

                }
            }
            gridItems(
                dataState.articles,
                columnCount = 2,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                paddingValues = PaddingValues(
                    horizontal = 20.dp
                )
            ) {
                ItemArticleGrid(
                    title = it.title,
                    message = it.body,
                    image = it.image,
                    onClick = {}
                )
            }
        }
    )

}


@Preview
@Composable
fun PreviewScreenHome() {
    BaseMainApp(
        bottomBar = {
            DashboardBottomNavigation(currentRoute = Home.routeName)
        }
    ) {
        ScreenHome(rememberApplicationState())
    }

}