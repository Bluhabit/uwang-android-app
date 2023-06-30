/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.dashboard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.extensions.formatToRupiah
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.Grey300
import com.bluehabit.core.ui.theme.LightBlue600
import com.bluehabit.core.ui.theme.Yellow400
import java.math.BigDecimal

@Composable
fun CardSummaryBudget(
    budgetAmount: BigDecimal = BigDecimal.ZERO,
    budgetUsed: BigDecimal = BigDecimal.ZERO,
    usage:String="",
    onEdit: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colors.surface)
            .padding(
                horizontal = 20.dp,
                vertical = 20.dp
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = stringResource(R.string.text_title_remaining_budget_dashboard_budget),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.fillMaxWidth(fraction = 0.7f),
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Text(
                text = stringResource(R.string.text_button_edit_summary_budget),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Medium,
                color = LightBlue600,
                modifier = Modifier
                    .clickable { onEdit() }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = budgetAmount.formatToRupiah(),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.text_label_usage_summary_budget, budgetUsed.formatToRupiah()),
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colors.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LinearProgressIndicator(
                progress = 0.5f,
                modifier = Modifier
                    .fillMaxWidth(
                        fraction = 0.7f
                    )
                    .height(16.dp),
                backgroundColor = Grey300,
                color = Yellow400,
                strokeCap = StrokeCap.Round
            )
            Text(
                text = stringResource(R.string.text_label_usage_percentage_summary_budget, usage),
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}

@Preview
@Composable
fun PreviewCardSummaryBudget() {
    BaseMainApp {
        CardSummaryBudget()
    }
}