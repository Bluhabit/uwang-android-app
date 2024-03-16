/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.alert

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun BasicAlert(
    modifier: Modifier = Modifier,
    leadingButton: @Composable () -> Unit,
    trailingButton: @Composable () -> Unit,
    message: String,
    messageColor: Color,
    backgroundColor: Color,
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(dimens.dp_36)
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .padding(horizontal = dimens.dp_8),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        leadingButton()
        Text(
            text = message,
            style = UwangTypography.LabelMedium.Medium,
            color = messageColor
        )
        Spacer(modifier = Modifier.weight(1f))
        trailingButton()
    }
}