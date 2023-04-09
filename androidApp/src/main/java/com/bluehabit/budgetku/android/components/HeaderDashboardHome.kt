/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.extensions.formatToRupiah
import java.math.BigDecimal

@Composable
fun HeaderDashboardHome(
    displayName: String = "",
    currentMonth: String = "",
    remainingBalance: BigDecimal = BigDecimal.ZERO,
    expenses: BigDecimal = BigDecimal.ZERO,
    income: BigDecimal = BigDecimal.ZERO,
    showBalance: Boolean = true,
    onShowBalance: (Boolean) -> Unit = {}
) {

    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardWidth = (currentWidth / 2) - 32.dp

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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.text_title_display_name_dashbaord_home, displayName),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(id = R.drawable.dummy_avatar),
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
            )

        }
        Spacer(modifier = Modifier.height(24.dp))
        Row {
            Text(
                text = stringResource(R.string.text_subtitle_remaining_balance_dashboard_home, currentMonth),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.onPrimary.copy(
                    alpha = 0.5f
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Icon(
                painter = painterResource(
                    id = if (showBalance) R.drawable.eye_open
                    else R.drawable.eye_close
                ),
                contentDescription = "",
                tint = Color.White.copy(
                    alpha = 0.5f
                ),
                modifier = Modifier.clickable {
                    onShowBalance(!showBalance)
                }
            )
        }
        Spacer(modifier = Modifier.height(9.dp))
        Text(
            text = remainingBalance.formatToRupiah(),
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
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_long_circle_up),
                        contentDescription = "",
                        tint = Color(0xFF57C45C)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.text_title_income_dashboar_home),
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colors.onPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = income.formatToRupiah(),
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
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_long_circle_down),
                        contentDescription = "",
                        tint = Color(0xFFFE3419)
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
                    text = expenses.formatToRupiah(),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewHeaderDashboard() {
    BaseMainApp {
        LazyColumn(content = {
            item {
                HeaderDashboardHome(
                    displayName = "Mario Jr.",
                    currentMonth = "April",
                    remainingBalance = BigDecimal(200000000),
                    expenses = BigDecimal(30000000),
                    income = BigDecimal(100000000)
                )
            }
        })
    }
}