/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.ui.Grey300
import com.bluehabit.budgetku.android.ui.Grey900

@Composable
fun ItemButtonGroup(
    labels: List<String> = listOf(),
    selected: String = "",
    onClick: (String) -> Unit = {}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardWidth = (currentWidth / labels.size) - 10.dp

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        labels.forEach { value ->
            Column(
                modifier = Modifier
                    .width(
                        cardWidth
                    )
                    .clip(MaterialTheme.shapes.small)
                    .background(
                        if (selected == value) MaterialTheme.colors.primary
                        else Color.Transparent
                    )
                    .clickable(
                        enabled = true,
                        onClick = {
                            onClick(value)
                        }
                    )
                    .border(
                        width = 1.dp,
                        shape = MaterialTheme.shapes.small,
                        color = Grey300
                    )
                    .padding(
                        vertical = 8.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = value,
                    color = if (selected == value) MaterialTheme.colors.onPrimary
                    else Grey900
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
        }

    }


}

@Preview
@Composable
fun PreviewItemButtonGroup() {
    BaseMainApp {
        ItemButtonGroup(
            labels = listOf(
                "12",
                "24",
                "48",
                "60"
            )
        )
    }
}