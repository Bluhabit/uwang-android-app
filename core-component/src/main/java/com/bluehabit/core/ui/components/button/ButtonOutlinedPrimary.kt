/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.theme.Grey900

@Composable
fun ButtonOutlinedPrimary(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.button,
    fontWeight: FontWeight = FontWeight.Medium,
    textColor: Color = MaterialTheme.colors.primary,
    fullWidth: Boolean = true,
    height: Dp = 45.dp,
    borderColor: Color = MaterialTheme.colors.primary,
    trailingIcon: Int = 0,
    contentPaddingValues: PaddingValues = ButtonDefaults.ContentPadding,
    onClick: () -> Unit = {}
) {
    val properties = if (fullWidth) modifier.fillMaxWidth() else modifier

    OutlinedButton(
        enabled = enabled,
        onClick = onClick,
        modifier = properties
            .height(height),
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.primary
        ),
        border = BorderStroke(
            width = 1.dp,
            color= borderColor
        ),
        contentPadding = contentPaddingValues,
    ) {
        Text(
            text = text,
            style = textStyle,
            fontWeight = fontWeight,
            color = textColor
        )
        if (trailingIcon != 0) {
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(id = trailingIcon),
                contentDescription = "",
                tint = Grey900
            )
        }
    }
}

@Preview
@Composable
fun PreviewBButtonOutlinedPrimary() {
    BaseMainApp {
        ButtonOutlinedPrimary(
            text = "Continue"
        )
    }
}