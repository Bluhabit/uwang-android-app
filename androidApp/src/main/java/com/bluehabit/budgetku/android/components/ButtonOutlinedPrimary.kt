/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.base.BaseMainApp

@Composable
fun ButtonOutlinedPrimary(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    fullWidth: Boolean = true,
    onClick: () -> Unit = {}
) {
    val properties = if (fullWidth) modifier.fillMaxWidth() else modifier

    OutlinedButton(
        enabled = enabled,
        onClick = onClick,
        modifier = properties
            .fillMaxWidth()
            .height(45.dp),
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.primary
        ),
        border = BorderStroke(
            width = 1.dp,
            color=MaterialTheme.colors.primary
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colors.primary
        )
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