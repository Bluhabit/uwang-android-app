/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.dashboard.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.extensions.formatToRupiah
import app.trian.mvi.ui.extensions.gridItems
import com.bluehabit.eureka.feature.dashboard.home.DashboardState
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.CardChallengeBudgeting
import com.bluehabit.core.ui.components.CardMonthlyBudget
import com.bluehabit.core.ui.components.HeaderDashboardHome
import com.bluehabit.core.ui.components.HeaderSection
import com.bluehabit.core.ui.components.ItemAccount
import com.bluehabit.core.ui.components.ItemArticleGrid
import com.bluehabit.core.ui.components.ItemTransaction
import com.bluehabit.core.ui.components.ItemTutorial
import com.bluehabit.core.ui.routes.Routes

@Composable
fun ScreenHome(
    state: DashboardState = DashboardState(),
    onShowBalance:(Boolean)->Unit={},
    onNavigate:(String)->Unit={},
    onNavigateSingleTop:(route:String,params:Array<out String>)->Unit={_,_->}
) {
    LazyColumn(
        content = {
            item {
                HeaderDashboardHome(
                    displayName = state.profile.fullName,
                    currentMonth = state.currentMonth,
                    remainingBalance = state.remainingBalance,
                    expenses = state.expenses,
                    income = state.income,
                    showBalance = state.showBalance,
                    onShowBalance = { onShowBalance(it) },
                    onShowProfile = {
                        //navigateSingleTop(Profile.routeName)
                    }
                )
            }
            item {
                HeaderSection(
                    title = stringResource(R.string.title_section_list_account_dashboard_home),
                    onClick = {
                        onNavigate(Routes.ListAccount.routeName)
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
                        items(state.accounts) {
                            ItemAccount(
                                icon = it.icon,
                                accountBalance = it.accountBalance,
                                accountBankName = it.accountName,
                                onClick = {
                                    onNavigateSingleTop(Routes.ListAccount.routeName, arrayOf())
                                }
                            )
                        }
                    })
            }
            item {
                HeaderSection(
                    title = stringResource(R.string.title_section_monthly_budgeting_dashboard_home),
                    onClick = {
                        onNavigateSingleTop(Routes.Budget.routeName, arrayOf())
                    }
                )
            }
            item {
                CardMonthlyBudget(
                    remainingBudget = state.remainingBudget,
                    usedAmount = state.usedAmount,
                    totalBudget = state.totalBudget,
                    score = state.score,
                    transactions = state.latestTransaction
                )
            }
            item {
                HeaderSection(
                    title = stringResource(R.string.title_section_new_transaction_dashboard_home),
                    onClick = {
                        onNavigateSingleTop(Routes.ListTransaction.routeName, arrayOf())
                    }
                )
            }
            items(state.transactions) {
                ItemTransaction(
                    transactionName = it.transactionName,
                    transactionAccountName = it.transactionAccountName,
                    transactionCategoryName = it.transactionCategory,
                    transactionDate = it.transactionDate,
                    transactionAmount = stringResource(
                        if (it.isTransactionExpenses) R.string.text_expenses else R.string.text_income,
                        it.transactionAmount.formatToRupiah()
                    ),
                    transactionIcon = it.transactionIcon,
                    isExpenses = it.isTransactionExpenses,
                    onClick = {
                        onNavigateSingleTop(Routes.DetailTransaction.routeName, arrayOf( it.transactionId))
                    }
                )
            }
            item {
                HeaderSection(
                    title = stringResource(R.string.title_section_challenge_dashboard_home),
                    onClick = {}
                )
            }
            items(state.challenge) {
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
                        items(state.tutorial) {
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
                data=state.articles,
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
    BaseMainApp {
        ScreenHome()
    }
}