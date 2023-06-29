/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.theme.Grey700

@Composable
fun HeaderStepWithProgress(
    modifier: Modifier = Modifier.fillMaxWidth(),
    progress: Int = 0,
    total: Int = 5,
    backgroundColor: Color = MaterialTheme.colors.onSurface,
    color: Color = MaterialTheme.colors.primary,
    iconColor: Color = Grey700,
    onBackPress: () -> Unit = {},
    onClose: () -> Unit = {}
) {

    Row(
        modifier = modifier
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            contentDescription = "",
            tint = iconColor,
            modifier = Modifier.clickable(
                enabled = true,
                onClick = onBackPress
            )
        )
        LinearProgressIndicator(
            progress = ((progress.toFloat() / total) * 100) / 100,
            modifier = Modifier
                .fillMaxWidth(
                    fraction = 0.7f
                )
                .height(8.dp),
            color = color,
            backgroundColor = backgroundColor.copy(
                alpha = 0.4f
            ),
            strokeCap = StrokeCap.Round
        )
        Text(
            text = "${progress}/${total}",
            style = MaterialTheme.typography.subtitle2,
            fontWeight = FontWeight.Medium,
            color = iconColor
        )
        Icon(
            imageVector = Icons.Outlined.Close,
            contentDescription = "",
            tint = iconColor,
            modifier = Modifier.clickable(
                enabled = true,
                onClick = onClose
            )
        )
    }
}

@Preview
@Composable
fun PreviewHeaderStepWithProgress() {
    BaseMainApp {
        HeaderStepWithProgress(
            progress = 1,
            total = 5
        )
    }
}