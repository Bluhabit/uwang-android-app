/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.label

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluhabit.core.ui.theme.Gray500

@Composable
fun BaseIconLabel(
    modifier: Modifier = Modifier,
    text: String,
    leadingIcon: @Composable () -> Unit,
) {
    BaseLabel {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .align(Alignment.Center)
        ) {
            leadingIcon()
            Text(
                text = text,
                style = MaterialTheme.typography.body2,
                lineHeight = 18.sp,
                fontWeight = FontWeight.W400,
                color = Gray500,
            )
        }
    }
}