/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluhabit.core.ui.ext.Empty
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangTheme
import com.bluhabit.core.ui.theme.UwangTypography

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
    borderColor: Color = UwangColors.Text.Disabled,
    textColor: Color = UwangColors.State.Primary.Main,
    onClick: () -> Unit = {}
) {
    OutlinedButton(
        modifier = modifier.height(40.dp),
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = UwangColors.Base.White,
            contentColor = if (error) UwangColors.State.Error.Main else UwangColors.State.Primary.Main,
            disabledContentColor = UwangColors.Text.Disabled
        ),
        border = BorderStroke(
            width = 1.dp,
            color = when {
                error -> UwangColors.State.Error.Main
                enabled -> borderColor
                else -> UwangColors.Text.Disabled
            }
        ),
    ) {
        Text(
            text = text,
            style = UwangTypography.BodySmall.Medium,
            color = textColor
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewButtonOutlinedPrimary() {
    UwangTheme {
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