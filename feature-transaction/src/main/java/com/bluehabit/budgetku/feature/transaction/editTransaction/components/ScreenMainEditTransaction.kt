/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.transaction.editTransaction.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.DottedLine
import com.bluehabit.core.ui.components.ItemEditTransactionDetail
import com.bluehabit.core.ui.components.button.ButtonOutlinedPrimary
import com.bluehabit.core.ui.theme.Blue800
import com.bluehabit.core.ui.theme.Grey300

@Composable
fun ScreenMainEditTransaction(
    isExpenses: Boolean = true,
    onChangeTransactionType: (Boolean) -> Unit = {},
    amount: String = "",
    onInputAmount: () -> Unit = {},
    transactionDate: String = "",
    onSelectTransactionDate: () -> Unit = {},
    categoryName: String = "",
    categoryIcon: Int = R.drawable.ic_food,
    onSelectCategory: () -> Unit = {},
    bankAccountName: String = "",
    bankAccountIcon: Int = com.bluehabit.budgetku.data.R.drawable.dummy_bank_jago,
    onSelectAccount: () -> Unit = {},
    onSubmit:()->Unit={}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardWidth = (currentWidth / 2) - 50.dp
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colors.primary
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp
                )

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .background(
                        MaterialTheme.colors.surface
                    )
                    .padding(
                        vertical = 20.dp
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                        )
                ) {
                    Text(
                        text = "Transaksi",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onSurface
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier
                                .width(cardWidth)
                                .height(50.dp)
                                .clip(MaterialTheme.shapes.small)
                                .clickable(
                                    enabled = true,
                                    onClick = {
                                        onChangeTransactionType(false)
                                    }
                                )
                                .border(
                                    width = 1.dp,
                                    shape = MaterialTheme.shapes.medium,
                                    color = Grey300
                                )
                                .background(if (isExpenses) MaterialTheme.colors.surface else Blue800)
                                .padding(
                                    vertical = 8.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.arrow_long_circle_up),
                                contentDescription = "",
                                tint = Color(0xFF57C45C)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Pemasukan",
                                style = MaterialTheme.typography.subtitle1,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.onSurface,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Row(
                            modifier = Modifier
                                .width(cardWidth)
                                .height(50.dp)
                                .clip(MaterialTheme.shapes.small)
                                .clickable(
                                    enabled = true,
                                    onClick = {
                                        onChangeTransactionType(true)
                                    }
                                )
                                .background(if (isExpenses) Blue800 else MaterialTheme.colors.surface)
                                .padding(
                                    vertical = 8.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_long_circle_down),
                                contentDescription = "",
                                tint = Color(0xFFFE3419)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Pengeluaran",
                                style = MaterialTheme.typography.subtitle1,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.onPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
                DottedLine()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp
                        )
                ) {
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "Detail Transaksi",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onSurface
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    ItemEditTransactionDetail(
                        icon = R.drawable.ic_rp,
                        value = amount,
                        onClick = onInputAmount
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    ItemEditTransactionDetail(
                        icon = categoryIcon,
                        value = categoryName,
                        isExpandable = true,
                        onClick = onSelectCategory,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    ItemEditTransactionDetail(
                        icon = R.drawable.ic_pencil,
                        value = "Jajan Gofood",
                        onClick = {},
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    ItemEditTransactionDetail(
                        icon = bankAccountIcon,
                        value = bankAccountName,
                        isExpandable = true,
                        onClick = onSelectAccount,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    ItemEditTransactionDetail(
                        icon = R.drawable.ic_calendar,
                        value = transactionDate,
                        isExpandable = true,
                        onClick = onSelectTransactionDate,
                    )

                }
            }

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                )
                .background(MaterialTheme.colors.surface)
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                )
        ) {
            ButtonOutlinedPrimary(
                text = "simpan",
                onClick = onSubmit
            )
        }

    }
}

@Preview
@Composable
fun PreviewScreenMainEditTransaction() {
    BaseMainApp {
        ScreenMainEditTransaction()
    }
}