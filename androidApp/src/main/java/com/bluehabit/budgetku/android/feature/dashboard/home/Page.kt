/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.base.extensions.bottomNavigationListener
import com.bluehabit.budgetku.android.base.extensions.gridItems
import com.bluehabit.budgetku.android.base.extensions.navigateSingleTop
import com.bluehabit.budgetku.android.base.listener.BottomNavigationListener
import com.bluehabit.budgetku.android.components.CardArticleGrid
import com.bluehabit.budgetku.android.components.CardChallengeBudgeting
import com.bluehabit.budgetku.android.components.CardMonthlyBudget
import com.bluehabit.budgetku.android.components.DashboardBottomNavigation
import com.bluehabit.budgetku.android.components.DashboardBottomNavigationMenu
import com.bluehabit.budgetku.android.components.HeaderDashboardHome
import com.bluehabit.budgetku.android.components.HeaderSectionDashboardHome
import com.bluehabit.budgetku.android.components.ItemAccount
import com.bluehabit.budgetku.android.components.ItemTransaction
import com.bluehabit.budgetku.android.rememberApplicationState
import java.math.BigDecimal

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
        bottomNavigationListener(object : BottomNavigationListener {
            override fun onRefresh(item: DashboardBottomNavigationMenu) {
                // remove empty

            }

            override fun onNavigate(item: DashboardBottomNavigationMenu) {
                navigateSingleTop(item.route)
            }

            override fun onFab() {
                // remove empty
            }

        })
    }
    LazyColumn(
        content = {
            item {
                HeaderDashboardHome(
                    displayName = dataState.displayName,
                    currentMonth = dataState.currentMonth,
                    remainingBalance = dataState.remainingBalance,
                    expenses = dataState.expenses,
                    income = dataState.income,
                    showBalance = state.showBalance,
                    onShowBalance = { commit { copy(showBalance = it) } }
                )
            }
            item {
                HeaderSectionDashboardHome(
                    title = stringResource(R.string.title_section_list_account_dashboard_home)
                ) {

                }
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    content = {
                        item {
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                        items(3) {
                            ItemAccount(
                                accountBalance = BigDecimal(100_000_000),
                                accountBankName = "Bank Jago"
                            )
                        }
                    })
            }
            item {
                HeaderSectionDashboardHome(
                    title = stringResource(R.string.title_section_monthly_budgeting_dashboard_home)
                ) {

                }
            }
            item {
                CardMonthlyBudget(
                    remainingBudget = dataState.remainingBudget,
                    usedAmount = dataState.usedAmount,
                    totalBudget = dataState.totalBudget,
                    score = dataState.score,
                    transactions = dataState.transactions
                )
            }
            item {
                HeaderSectionDashboardHome(
                    title = stringResource(R.string.title_section_new_transaction_dashboard_home)
                ) {

                }
            }
            items(3) {
                ItemTransaction(
                    transactionName = "McDonald",
                    transactionAccountName = "Bank BCA",
                    transactionCategoryName = "Makanan",
                    transactionDate = "1 April 2023",
                    transactionAmount = "+Rp90.000",
                    transactionType = "Uang Masuk",
                    color = Color(0xFF57C45C)
                )
            }
            item {
                HeaderSectionDashboardHome(
                    title = stringResource(R.string.title_section_challenge_dashbaord_home)
                ) {

                }
            }
            items(2) {
                Box(
                    modifier = Modifier.padding(
                        horizontal = 20.dp,
                        vertical = 8.dp
                    )
                ) {
                    CardChallengeBudgeting(
                        title = "Follow the money trail",
                        message = "Answer question on budgeting and savings",
                        image = R.drawable.ic_dummy_challenge,
                        point = 1823,
                        pointTarget = 2000,
                        onClick = {}
                    )
                }
            }
            item {
                HeaderSectionDashboardHome(
                    title = stringResource(R.string.title_section_tutorial_dashboard_home)
                ) {

                }
            }
            item {
                Text(
                    text = "Need Improvement UI/UX",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            item {
                HeaderSectionDashboardHome(
                    title = stringResource(R.string.title_section_article_dashboard_home)
                ) {

                }
            }
            gridItems(
                4,
                columnCount = 2,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                paddingValues = PaddingValues(
                    horizontal = 20.dp
                )
            ) {

                CardArticleGrid(
                    title = "Cerdas Finansial",
                    message = "Yuk melek finansial bersama Budgetku.Tersedia course keuangan untuku",
                    image = R.drawable.ic_dummy_article,
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