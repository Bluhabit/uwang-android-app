/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.ui.BudgetKuTheme
import com.bluehabit.budgetku.android.ui.Grey300
import com.bluehabit.budgetku.android.ui.Grey900

@Composable
fun CardPointProfile(
    icon: Int = R.drawable.ic_coin_rectangle,
    text: List<AnnotationTextItem> = listOf(),
    textButton: String = "",
    onClick: () -> Unit = {}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardWidth = (currentWidth / 2) - 20.dp

    Box(
        modifier = Modifier
            .width(cardWidth)
            .clip(MaterialTheme.shapes.medium)
            .border(
                width = 1.dp,
                shape = MaterialTheme.shapes.medium,
                color = Grey300
            )
            .padding(
                horizontal = 16.dp,
                vertical = 16.dp
            )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    modifier = Modifier.size(35.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextAnnotationWithStyle(
                    labels = text
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = textButton,
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colors.primary)
                    .clickable(
                        enabled = true,
                        onClick = onClick
                    )
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
            )
        }

    }
}

@Preview
@Composable
fun PreviewCardPointProfile() {
    BudgetKuTheme {
        CardPointProfile(
            text = listOf(
                AnnotationTextItem.Text("Masuk dan ambil"),
                AnnotationTextItem.Text(
                    "40",
                    style = MaterialTheme.typography.button.copy(
                        color = Grey900,
                        fontWeight = FontWeight.Bold
                    )
                ),
                AnnotationTextItem.Text("koin"),
            ),
            textButton = "Collect"
        )
    }
}