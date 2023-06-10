/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.BudgetKuTheme

@Composable
fun ItemEditTransactionDetail(
    icon: Int = 0,
    value: String = "",
    isExpandable: Boolean = false,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .background(Color(0xFFfafafa))
            .border(
                BorderStroke(1.5.dp, Color(0xFFe0e0e0)),
                RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "",
            modifier = Modifier.size(
                20.dp
            )
        )
        Text(
            modifier = Modifier.weight(1f),
            text = value,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Medium
        )
        if (isExpandable) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_short_down),
                contentDescription = "",
            )
        }
    }
}


@Preview
@Composable
fun PreviewItemEditTransactionDetail() {
    BudgetKuTheme {
        ItemEditTransactionDetail(
            icon = R.drawable.ic_pencil,
            value = "Jajan Gofood",
            onClick = {},
        )
    }
}