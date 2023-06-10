/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.dashboard.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.extensions.formatToRupiah
import com.bluehabit.core.ui.theme.Grey300
import com.bluehabit.core.ui.theme.Grey700
import java.math.BigDecimal

@Composable
fun ItemTipsBudgetSuccess(
    amount: BigDecimal = BigDecimal.ZERO
) {
    Spacer(modifier = Modifier.height(24.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    MaterialTheme.shapes.medium
                )
                .border(
                    width = 1.dp,
                    shape = MaterialTheme.shapes.medium,
                    color = Grey300
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(
                        fraction = 0.8f
                    )
                    .padding(
                        start = 16.dp,
                        top = 16.dp,
                        bottom = 16.dp
                    ),
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(
                            Color(0xFFEDFFEE)
                        )
                        .border(
                            width = 1.dp,
                            shape = CircleShape,
                            color = Color(0xFF57C45C)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_money_setting),
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.Center)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
                                fontWeight = FontWeight.Medium
                            )
                        ) {
                            append(stringResource(R.string.text_tips_budget_success_first))
                            append(" ")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(amount.formatToRupiah())
                        }
                        withStyle(
                            style = SpanStyle(
                                fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
                                fontWeight = FontWeight.Medium
                            )
                        ) {
                            append(stringResource(R.string.text_tips_budget_success_end))
                        }
                    },
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Normal,
                    color = Grey700,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

            }
            Box(
                modifier = Modifier
                    .width(25.dp)
                    .height(72.dp)
                    .background(
                        Color(0xFFEDFFEE)
                    )
            ) {

            }

        }

    }
}

@Preview
@Composable
fun PreviewItemTipsBudgetSuccess() {
    BaseMainApp {
        ItemTipsBudgetSuccess()
    }
}