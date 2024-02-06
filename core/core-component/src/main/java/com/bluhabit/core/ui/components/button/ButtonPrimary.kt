/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
 * @sample PreviewButtonPrimary
 * */

@Composable
fun ButtonPrimary(
    modifier: Modifier = Modifier,
    text: String = String.Empty,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier.height(40.dp),
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = UwangColors.State.Primary.Main,
            contentColor = UwangColors.Base.White,
            disabledBackgroundColor = UwangColors.Palette.Neutral.Grey3,
            disabledContentColor = UwangColors.Palette.Neutral.Grey5,
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp
        )
    ) {
        Text(
            text = text,
            style = UwangTypography.BodySmall.Medium,
            color = UwangColors.Base.White
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewButtonPrimary() {
    UwangTheme {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
        ) {
            ButtonPrimary(
                modifier = Modifier,
                text = "Button CTA",
                enabled = true,
                onClick = {}
            )
            ButtonPrimary(
                modifier = Modifier,
                text = "Button CTA disabled",
                enabled = false,
                onClick = {}
            )
        }
    }
}