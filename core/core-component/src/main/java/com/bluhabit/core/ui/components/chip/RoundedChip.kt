/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.ext.Empty
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTheme
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun RoundedChip(
    text: String = String.Empty,
    selected: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .clip(CircleShape)
            .background(
                color = if (selected) UwangColors.State.Primary.Surface else UwangColors.Base.White
            )
            .border(
                width = 1.dp,
                color = if (selected) UwangColors.State.Primary.Main else UwangColors.Text.Border,
                shape = CircleShape
            )
            .clickable(onClick = onClick, enabled = enabled, interactionSource = remember { MutableInteractionSource() }, indication = null)
            .padding(horizontal = dimens.dp_12, vertical = dimens.dp_8)
    ) {
        Text(
            text = text,
            style = if (selected) UwangTypography.BodySmall.Medium else UwangTypography.BodySmall.Regular,
            color = UwangColors.Text.Main
        )
        if (selected) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "",
                tint = UwangColors.Text.Secondary,
                modifier = Modifier
                    .size(dimens.dp_16)
            )
        }
    }
}

@Preview
@Composable
fun RoundedChipPreview() {
    var selected by remember {
        mutableStateOf(false)
    }
    UwangTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            RoundedChip(
                text = "\uD83D\uDCD1 Manajemen anggaran",
                selected = selected,
            ) {
                selected = !selected
            }
        }
    }
}