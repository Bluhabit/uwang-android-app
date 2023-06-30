/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.dashboard.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
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
import app.trian.mvi.ui.extensions.dashedBorder
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.theme.Grey300
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.Blue600

@Composable
fun CardEmptyTransactionBudget(
    onCreateTransaction:()->Unit={}
) {
    Spacer(modifier = Modifier.height(24.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp
            )
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    MaterialTheme.shapes.medium
                )
                .dashedBorder(
                    border = BorderStroke(
                        width = 1.dp,
                        color = Grey300
                    ),
                    shape = MaterialTheme.shapes.medium,
                    on = 6.dp,
                    off = 5.dp
                )
                .padding(
                    all = 16.dp
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(
                        fraction = 0.8f
                    )
                ) {
                    Text(
                        text = stringResource(R.string.text_message_information_category_dashboard_budget),
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                enabled = true,
                                onClick = onCreateTransaction
                            ),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.text_button_add_transaction_dashboard_budget),
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Bold,
                            color = Blue600
                        )
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowRight,
                            contentDescription = "",
                            tint = Blue600
                        )
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_search_category_dashboard_category),
                    contentDescription = "",
                    modifier = Modifier.size(70.dp)

                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCardEmptyTransactionBudget() {
    BaseMainApp {
        CardEmptyTransactionBudget()
    }
}