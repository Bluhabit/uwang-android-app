/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.transaction.listTransaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.extensions.formatToRupiah
import app.trian.mvi.ui.internal.UIContract
import app.trian.mvi.ui.internal.rememberUIController
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.BaseScreen
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.ItemTransaction
import com.bluehabit.core.ui.components.input.FormInputSearch
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.routes.TransactionConstants
import com.bluehabit.core.ui.theme.Grey300

@Navigation(
    route = Routes.ListTransaction.routeName,
    viewModel = ListTransactionViewModel::class
)
@Composable
fun ListTransactionScreen(
   uiContract: UIContract<ListTransactionState,ListTransactionIntent,ListTransactionAction>
) = UIWrapper(uiContract) {

    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardWidth = (currentWidth / 2) - 32.dp

    BaseScreen(
        topAppBar = {
            TopAppbarListTransaction(
                onBackPressed = {
                    navigator.navigateUp()
                }
            )
        }
    ) {
        LazyColumn(content = {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.primary)
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            bottom = 32.dp
                        ),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier.height(30.dp))
                    Row {
                        Text(
                            text = stringResource(R.string.text_subtitle_remaining_balance_dashboard_home, "April"),
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colors.onPrimary.copy(
                                alpha = 0.5f
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                    }
                    Spacer(modifier = Modifier.height(9.dp))
                    Text(
                        text = state.balance.formatToRupiah(),
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .width(cardWidth)
                                .clip(MaterialTheme.shapes.small)
                                .background(Color(0xFF1952CE))
                                .padding(
                                    vertical = 8.dp
                                )
                                .height(cardWidth / 3),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row {
                                Image(
                                    painter = painterResource(id = R.drawable.arrow_long_circle_up),
                                    contentDescription = ""
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = stringResource(R.string.text_title_income_dashboard_home),
                                    style = MaterialTheme.typography.body2,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colors.onPrimary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = state.income.formatToRupiah(),
                                style = MaterialTheme.typography.subtitle1,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.onPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Column(
                            modifier = Modifier
                                .width(cardWidth)
                                .clip(MaterialTheme.shapes.small)
                                .background(Color(0xFF1952CE))
                                .padding(
                                    vertical = 8.dp
                                )
                                .height(cardWidth / 3),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row {
                                Image(
                                    painter = painterResource(id = R.drawable.arrow_long_circle_down),
                                    contentDescription = "",
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = stringResource(R.string.text_title_expense_dashboard_home),
                                    style = MaterialTheme.typography.body2,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colors.onPrimary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = state.expenses.formatToRupiah(),
                                style = MaterialTheme.typography.subtitle1,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.onPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(
                                0.8f
                            )
                        ) {
                            FormInputSearch(
                                placeholder = "Cari Transaksi"
                            )

                        }
                        Icon(
                            painter = painterResource(id = R.drawable.ic_filter_circle),
                            contentDescription = "",
                            tint = MaterialTheme.colors.onPrimary
                        )

                    }
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Grey300)
                        .padding(
                            horizontal = 20.dp,
                            vertical = 16.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "24 Mei 2023",
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colors.onSurface
                    )
                    Text(
                        text = "-${state.currentExpense.formatToRupiah()}",
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }
            items(state.transactions) {
                ItemTransaction(
                    transactionName = it.transactionName,
                    transactionAccountName = it.transactionAccountName,
                    transactionCategoryName = it.transactionCategory,
                    transactionDate = it.transactionDate.toString(),
                    transactionAmount = stringResource(
                        if (it.isTransactionExpenses) R.string.text_expenses else R.string.text_income,
                        it.transactionAmount.formatToRupiah()
                    ),
                    isExpenses = it.isTransactionExpenses,
                    onClick = {
                        navigator.navigateSingleTop(Routes.DetailTransaction.routeName, it.transactionId)
                    },
                    transactionIcon = it.transactionIcon
                )
            }
        })
    }
}

@Composable
fun TopAppbarListTransaction(
    onBackPressed: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .height(40.dp)
            .background(
                MaterialTheme.colors.primary
            )
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            contentDescription = "",
            modifier = Modifier.clickable(
                enabled = true,
                onClick = onBackPressed
            ),
            tint = MaterialTheme.colors.onPrimary
        )
        Text(
            text = "Transaksi",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onPrimary
        )
        Spacer(modifier = Modifier.width(8.dp))

    }
}

@Preview
@Composable
fun PreviewScreenListTransaction() {
    BaseMainApp() {
        ListTransactionScreen(
           uiContract = UIContract(
               controller = rememberUIController(),
               state = ListTransactionState()
           )
        )
    }
}