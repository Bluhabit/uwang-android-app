/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.theme.Grey300
import com.bluehabit.core.ui.theme.Grey700
import com.bluehabit.core.ui.theme.Grey900

@Composable
fun ItemTransaction(
    transactionName: String = "",
    transactionAccountName: String = "",
    transactionDate: String = "",
    transactionCategoryName: String = "",
    transactionAmount: String = "",
    transactionIcon: Int = com.bluehabit.budgetku.data.R.drawable.ic_dummy_food,
    isExpenses: Boolean = false,
    clickable: Boolean = true,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(enabled = clickable, onClick = onClick)
                .padding(
                    top = 16.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 16.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = transactionIcon),
                    contentDescription = "",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = transactionName,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = transactionAccountName,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Normal,
                        color = Grey700
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = transactionDate,
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Normal,
                            color = Grey700
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .size(4.dp)
                                .clip(CircleShape)
                                .background(Grey300)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = transactionCategoryName,
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Normal,
                            color = Grey900
                        )

                    }

                }
            }

            Column {
                Text(
                    text = transactionAmount,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Medium,
                    color = if (isExpenses) Color.Red else Color(0xFF57C45C)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (isExpenses) "Uang Keluar" else "Uang Masuk",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Normal,
                    color = Grey700
                )
            }

        }
        Divider()
    }
}

@Preview
@Composable
fun PreviewItemTransaction() {
    BaseMainApp {
        LazyColumn(content = {
            items(3) {
                ItemTransaction(
                    transactionName = "McDonald",
                    transactionAccountName = "Bank BCA",
                    transactionCategoryName = "Makanan",
                    transactionDate = "1 April 2023",
                    transactionAmount = "+Rp90.000",
                    isExpenses = false
                )
            }
        })
    }
}