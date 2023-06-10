/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.dashboard.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.extensions.formatToRupiah
import com.bluehabit.core.ui.theme.Grey100
import java.math.BigDecimal

@Composable
fun ItemExpensesCategoryBudget(
    categoryName:String="",
    categoryImage:Int=0,
    usage:String="",
    amount: BigDecimal = BigDecimal.ZERO,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = true,
                onClick = onClick
            )
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 16.dp
            ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(
                    fraction = 0.6f
                )
            ) {
                Column(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Grey100)
                        .padding(all = 4.dp),
                ) {
                    Image(
                        painter = painterResource(id = categoryImage),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = categoryName,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.text_label_usage_category_budget, usage),
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Text(
                text = amount.formatToRupiah(),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
    }
}

@Preview
@Composable
fun PreviewItemTransactionBudget() {
    BaseMainApp {
        LazyColumn(content = {
            items(3) {
                ItemExpensesCategoryBudget(
                    amount = BigDecimal(1_000_000),
                    categoryName = "Makan&Minum",
                    categoryImage = com.bluehabit.budgetku.data.R.drawable.ic_dummy_food,
                    usage = "50%"
                )
            }
        })
    }
}