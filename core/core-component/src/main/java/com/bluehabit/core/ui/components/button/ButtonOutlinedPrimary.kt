/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.extensions.Empty
import app.trian.mvi.ui.extensions.from
import com.bluehabit.core.ui.theme.Error300
import com.bluehabit.core.ui.theme.Error700
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Primary300
import com.bluehabit.core.ui.theme.Primary700

/**
 * Button Primary
 * @param modifier
 *
 * @sample PreviewButtonOutlinedPrimary
 * */

@Composable
fun ButtonOutlinedPrimary(
    modifier: Modifier = Modifier,
    text: String = String.Empty,
    enabled: Boolean = true,
    error: Boolean = false,
    onClick: () -> Unit = {}
) {
    val ctx = LocalContext.current
    OutlinedButton(
        modifier = modifier.height(44.dp.from(context = ctx)),
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent,
            contentColor = if (error) Error700 else Primary700,
            disabledContentColor = if (error) Error300 else Primary300
        ),
        border = BorderStroke(
            width = 1.dp,
            color = when {
                error -> if (enabled) Error700 else Error300
                else -> if (enabled) Primary700 else Primary300
            }
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewButtonOutlinedPrimary() {
    GaweanTheme {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
        ) {
            ButtonOutlinedPrimary(
                modifier = Modifier,
                text = "Button CTA",
                enabled = true,
                onClick = {}
            )
            ButtonOutlinedPrimary(
                modifier = Modifier,
                text = "Button CTA disabled",
                enabled = false,
                onClick = {}
            )
            ButtonOutlinedPrimary(
                modifier = Modifier,
                text = "Button CTA error",
                enabled = true,
                error = true,
                onClick = {}
            )
            ButtonOutlinedPrimary(
                modifier = Modifier,
                text = "Button CTA error disabled",
                enabled = false,
                error = true,
                onClick = {}
            )
        }

    }
}