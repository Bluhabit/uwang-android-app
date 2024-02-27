/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTheme

@Composable
fun BaseProfileCard(
    modifier: Modifier = Modifier,
    background: Color = UwangColors.Text.LayoutBackground,
    borderColor: Color = UwangColors.Text.Separator,
    height: Dp = 90.dp,
    onCardClick: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit = {}
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    Box(
        modifier = modifier
            .border(width = dimens.from(2.dp), color = borderColor, shape = RoundedCornerShape(dimens.dp_12))
            .clip(RoundedCornerShape(dimens.dp_12))
            .background(background)
            .fillMaxWidth()
            .height(dimens.from(height))
            .clickable(onClick = onCardClick),
        content = content
    )
}

@Preview
@Composable
fun BaseProfileCardPreview() {
    UwangTheme {
        BaseProfileCard(
            modifier = Modifier
                .padding(top = 56.dp, start = 16.dp, end = 16.dp)
                .safeDrawingPadding()
        )
    }
}