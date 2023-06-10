/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.dashboard.budget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.feature.dashboard.component.CardEmptyBudget
import com.bluehabit.budgetku.feature.dashboard.component.CardEmptyTransactionBudget
import com.bluehabit.budgetku.feature.dashboard.component.CardSummaryBudget
import com.bluehabit.budgetku.feature.dashboard.component.ItemExpensesCategoryBudget
import com.bluehabit.budgetku.feature.dashboard.component.ItemTipsBudgetEmpty
import com.bluehabit.budgetku.feature.dashboard.component.ItemTipsBudgetSuccess
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.UIListenerData
import com.bluehabit.core.ui.UiWrapperData
import com.bluehabit.core.ui.theme.Blue800
import com.bluehabit.core.ui.theme.Grey500
import java.math.BigDecimal

@Composable
fun ScreenBudget(
    state:BudgetState= BudgetState(),
    data:BudgetDataState= BudgetDataState(),
    invoker:UIListenerData<BudgetState,BudgetDataState,BudgetEvent>
) = UiWrapperData(invoker) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(fraction = 0.4f)
                        .background(Blue800)
                        .padding(
                            bottom = 20.dp,
                            start = 20.dp,
                            end = 20.dp
                        ),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            commitData { copy(hasBudget = false) }
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.KeyboardArrowLeft,
                                contentDescription = "",
                                tint = MaterialTheme.colors.onPrimary
                            )
                        }
                        Text(
                            text = data.currentMonth,
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onPrimary
                        )
                        IconButton(onClick = { commitData { copy(hasBudget = true) } }) {
                            Icon(
                                imageVector = Icons.Outlined.KeyboardArrowRight,
                                contentDescription = "",
                                tint = MaterialTheme.colors.onPrimary
                            )
                        }
                    }

                    if (data.hasBudget) {
                        CardSummaryBudget(
                            budgetAmount = BigDecimal(10_000_000),
                            budgetUsed = BigDecimal(1_000_000),
                            usage = "40%",
                            onEdit = {
                              //  navigateSingleTop(CreateBudget.routeName)
                            }
                        )
                    } else {
                        CardEmptyBudget(
                            onCreateNewBudget = {
                            //    navigateSingleTop(CreateBudget.routeName)
                            },
                            onHelpClick = {
                               // navigateSingleTop(TutorialBudget.routeName)
                            }
                        )
                    }

                }
            }
            item {

                if (data.hasBudget) {
                    ItemTipsBudgetSuccess(
                        amount = BigDecimal(191_000),
                    )
                } else {
                    ItemTipsBudgetEmpty(

                    )
                }

            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp
                        )
                ) {

                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.title_section_category_expenses_dashboard_budget),
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onSurface
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sort_dashboard_budget),
                            contentDescription = "",
                            tint = Grey500,
                            modifier = Modifier.clickable(
                                enabled = true,
                                onClick = {
                                    commit {
                                        copy(bottomSheetType = BottomSheetBudget.SORT)
                                    }
                                    showBottomSheet()
                                }
                            )
                        )
                    }
                }
            }
            if (!data.hasBudget) {
                item {
                    CardEmptyTransactionBudget(
                        onCreateTransaction = {
                        //    navigateSingleTop(CreateTransaction.routeName)
                        }
                    )
                }
            }

            if (data.hasBudget) {
                items(data.expensesCategory) {
                    ItemExpensesCategoryBudget(
                        amount = it.amount,
                        categoryName = it.categoryName,
                        categoryImage = it.categoryImage,
                        usage = it.usage,
                        onClick = {
                           // navigateSingleTop(DetailTransaction.routeName)
                        }
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewScreenBudget() {
    BaseMainApp {
        ScreenBudget(
            invoker = UIListenerData(
                controller = it,
                state = BudgetState(),
                data= BudgetDataState()
            )
        )
    }
}