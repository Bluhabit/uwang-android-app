/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.dashboard.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.theme.Grey300
import com.bluehabit.core.ui.theme.Grey700

@Composable
fun ItemTipsBudgetEmpty() {
    Spacer(modifier = Modifier.height(24.dp))
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
                .clip(
                    MaterialTheme.shapes.medium
                )
                .border(
                    width = 1.dp,
                    shape = MaterialTheme.shapes.medium,
                    color = Grey300
                )
                .padding(
                    all = 16.dp
                )
        ) {
            Text(
                text = stringResource(R.string.text_message_tips_and_tricks_dashboard_budget),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Normal,
                color = Grey700,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}

@Preview
@Composable
fun PreviewItemTipsBudgetEmpty() {
    BaseMainApp {
        ItemTipsBudgetEmpty()
    }
}