/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.dashboard.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.base.extensions.gridItems
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.BaseScreen
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.UIListenerData
import com.bluehabit.core.ui.UiWrapperData
import com.bluehabit.core.ui.components.CardChallengeBudgeting
import com.bluehabit.core.ui.components.CardMonthlyBudget
import com.bluehabit.core.ui.components.HeaderDashboardHome
import com.bluehabit.core.ui.components.HeaderSection
import com.bluehabit.core.ui.components.ItemAccount
import com.bluehabit.core.ui.components.ItemArticleGrid
import com.bluehabit.core.ui.components.ItemTransaction
import com.bluehabit.core.ui.components.ItemTutorial
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetAddBudgetTransaction
import com.bluehabit.core.ui.extensions.formatToRupiah
import com.bluehabit.core.ui.routes.Routes

@Composable
fun ScreenHome(
    state: HomeState = HomeState(),
    data: HomeDataState = HomeDataState(),
    invoker: UIListenerData<HomeState, HomeDataState, HomeEvent>
) = UiWrapperData(
    invoker
) {
    BaseScreen(
        bottomSheet = {
            BottomSheetAddBudgetTransaction(
                onDismiss = {
                    hideBottomSheet()
                },
                onAddAccount = {
                    hideBottomSheet()
                    //  navigateSingleTop(CreateAccount.routeName)
                },
                onAddTransaction = {
                    hideBottomSheet()
                    //navigateSingleTop(CreateTransaction.routeName)
                },
                onAddBudget = {
                    hideBottomSheet()
                    //navigateSingleTop(CreateBudget.routeName)
                },
                onAddTransfer = {},
            )
        }
    ) { }



    LazyColumn(
        content = {
            item {
                HeaderDashboardHome(
                    displayName = data.profile.fullName,
                    currentMonth = data.currentMonth,
                    remainingBalance = data.remainingBalance,
                    expenses = data.expenses,
                    income = data.income,
                    showBalance = state.showBalance,
                    onShowBalance = { commit { copy(showBalance = it) } },
                    onShowProfile = {
                        //navigateSingleTop(Profile.routeName)
                    }
                )
            }
            item {
                HeaderSection(
                    title = stringResource(R.string.title_section_list_account_dashboard_home),
                    onClick = {
                        navigateSingleTop(Routes.ListAccount.routeName)
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
                        items(data.accounts) {
                            ItemAccount(
                                icon = it.icon,
                                accountBalance = it.accountBalance,
                                accountBankName = it.accountName,
                                onClick = {
                                   // navigateSingleTop(ListAccount.routeName)
                                }
                            )
                        }
                    })
            }
            item {
                HeaderSection(
                    title = stringResource(R.string.title_section_monthly_budgeting_dashboard_home),
                    onClick = {
                        navigateSingleTop(Routes.Budget.routeName)
                    }
                )
            }
            item {
                CardMonthlyBudget(
                    remainingBudget = data.remainingBudget,
                    usedAmount = data.usedAmount,
                    totalBudget = data.totalBudget,
                    score = data.score,
                    transactions = data.latestTransaction
                )
            }
            item {
                HeaderSection(
                    title = stringResource(R.string.title_section_new_transaction_dashboard_home),
                    onClick = {
                        // navigateSingleTop(ListTransaction.routeName)
                    }
                )
            }
            items(data.transactions) {
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
                        // navigateSingleTop(DetailTransaction.routeName,it.transactionId)
                    }
                )
            }
            item {
                HeaderSection(
                    title = stringResource(R.string.title_section_challenge_dashboard_home),
                    onClick = {}
                )
            }
            items(data.challenge) {
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
                        items(data.tutorial) {
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
                data=data.articles,
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
    BaseMainApp() {
        ScreenHome(
            invoker = UIListenerData(
                controller = it,
                state = HomeState(),
                data = HomeDataState()
            )
        )
    }

}