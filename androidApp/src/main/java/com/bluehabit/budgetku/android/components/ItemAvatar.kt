/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.ui.BudgetKuTheme

@Composable
fun ItemAvatar(
    avatar: Int = com.bluehabit.budgetku.data.R.drawable.dummy_avatar,
    selected: Boolean = false,
    onClick: () -> Unit = {}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardWidth = (currentWidth / 2) - 40.dp

    Box(
        modifier = Modifier
            .size(cardWidth)
    ) {
        Box(
            modifier = Modifier
                .size(cardWidth)
                .clip(CircleShape)
                .clickable(
                    enabled = true,
                    onClick = onClick
                )
                .border(
                    width = if (selected) 1.dp else 0.dp,
                    shape = CircleShape,
                    color = if (selected) MaterialTheme.colors.primary else Color.Transparent
                )
                .padding(
                    all = if (selected) 6.dp else 0.dp
                )

        ) {
            Image(
                painter = painterResource(id = avatar),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(cardWidth)
                    .clip(CircleShape)
            )
        }
        if (selected) {
            Image(
                painter = painterResource(id = R.drawable.ic_check_selected_avatar),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(40.dp)
            )
        }
    }

}

@Preview
@Composable
fun PreviewItemAvatar() {
    BudgetKuTheme {
        Column {
            ItemAvatar()
            ItemAvatar(
                selected = true
            )
        }
    }
}