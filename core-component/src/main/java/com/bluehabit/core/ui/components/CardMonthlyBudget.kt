/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.extensions.formatToRupiah
import com.bluehabit.budgetku.data.model.LatestTransactionMonthly
import com.bluehabit.budgetku.data.remote.dummy.dummyLatestMonthlyBudget
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.Grey100
import com.bluehabit.core.ui.theme.Grey300
import com.bluehabit.core.ui.theme.LightBlue600
import java.math.BigDecimal


@Composable
fun CardMonthlyBudget(
    remainingBudget: BigDecimal = BigDecimal.ZERO,
    usedAmount: BigDecimal = BigDecimal.ZERO,
    totalBudget: BigDecimal = BigDecimal.ZERO,
    score: Int = 0,
    transactions: List<LatestTransactionMonthly> = listOf()
) {
    val color = when {
        usedAmount >= totalBudget -> Color(0xFFFE3419)
        usedAmount >= remainingBudget -> Color(0xFFFEA316)
        else -> Color(0xFF57C45C)
    }

    var visible by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp
            )
            .clip(
                MaterialTheme.shapes.medium
            )
            .border(
                width = 1.dp,
                color = Grey300,
                shape = MaterialTheme.shapes.medium
            )
            .background(MaterialTheme.colors.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.title_card_remaining_budget_dashboard_home),
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = remainingBudget.formatToRupiah(),
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        color = color,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(17.dp))
                LinearProgressIndicator(
                    progress = 0.5f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    backgroundColor = Grey300,
                    color = color,
                    strokeCap = StrokeCap.Round
                )
                Spacer(modifier = Modifier.height(17.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
                                fontWeight = FontWeight.Medium
                            ),
                        ) {
                            append(stringResource(R.string.text_card_used_budget_dashboard_home))
                        }
                        withStyle(
                            style = SpanStyle(
                                fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
                                fontWeight = FontWeight.SemiBold
                            ),
                        ) {
                            append(" ")
                            append(usedAmount.formatToRupiah())
                        }
                        withStyle(
                            style = SpanStyle(
                                fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
                                fontWeight = FontWeight.Medium
                            ),
                        ) {
                            append("/")
                            append(totalBudget.formatToRupiah())
                        }

                    },
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(17.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Grey100)
                    .padding(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.text_card_score_financial_dashboard_home),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
                                fontWeight = FontWeight.SemiBold
                            ),
                        ) {
                            append("$score")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
                                fontWeight = FontWeight.Normal
                            ),
                        ) {
                            append("/")
                            append("100")
                        }
                    },
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            AnimatedVisibility(visible = visible) {
                Column {
                    transactions.forEach {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 16.dp,
                                    vertical = 16.dp
                                ),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row {
                                Image(
                                    painter = painterResource(id = it.icon),
                                    contentDescription = "",
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = it.transactionName,
                                    style = MaterialTheme.typography.subtitle1,
                                    fontWeight = FontWeight.Medium,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
                                            fontWeight = FontWeight.Normal
                                        ),
                                    ) {
                                        append(stringResource(id = R.string.text_card_used_budget_dashboard_home))
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
                                            fontWeight = FontWeight.SemiBold
                                        ),
                                    ) {
                                        append(" ")
                                        append(it.usedAmount.formatToRupiah())
                                    }
                                },
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Divider()
                    }
                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        visible = !visible
                    }
                    .padding(
                        top = 16.dp,
                        bottom = 16.dp
                    ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(
                        if (visible) R.string.text_button_hide_card_monthly_dashboard_home
                        else R.string.text_button_expand_card_monthly_dashboard_home
                    ),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Medium,
                    color = LightBlue600,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(
                    imageVector = if (visible) Icons.Outlined.KeyboardArrowUp else Icons.Outlined.KeyboardArrowDown,
                    contentDescription = ""
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCardMonthlyBudget() {
    BaseMainApp {
        CardMonthlyBudget(
            remainingBudget = BigDecimal(2_000_000),
            usedAmount = BigDecimal(3_000_000),
            totalBudget = BigDecimal(4_000_000),
            score = 50,
            transactions = dummyLatestMonthlyBudget
        )
    }
}