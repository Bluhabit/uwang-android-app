/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluhabit.core.ui.ext.Empty
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.Error200
import com.bluhabit.core.ui.theme.Gray300
import com.bluhabit.core.ui.theme.Primary200
import com.bluhabit.core.ui.theme.UwangTheme

/**
 * Button Primary
 * @param modifier
 *
 * @sample PreviewButtonTextPrimary
 * */

@Composable
fun ButtonTextPrimary(
    modifier: Modifier = Modifier,
    text: String = String.Empty,
    enabled: Boolean = true,
    error: Boolean = false,
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val ctx = LocalContext.current
    TextButton(
        modifier = modifier.height(44.dp),
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = if (isPressed)
                if (error) Error200
                else Primary200
            else Color.Transparent,
            contentColor = if (error) UwangColors.Error.Red500 else UwangColors.Primary.Blue500,
            disabledContentColor = if (error) UwangColors.Error.Red300 else Gray300
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp
        ),
        interactionSource = MutableInteractionSource()
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewButtonTextPrimary() {
    UwangTheme {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
        ) {
            ButtonTextPrimary(
                modifier = Modifier,
                text = "Button CTA",
                enabled = true,
                onClick = {}
            )
            ButtonTextPrimary(
                modifier = Modifier,
                text = "Button CTA disabled",
                enabled = false,
                onClick = {}
            )
            ButtonTextPrimary(
                modifier = Modifier,
                text = "Button CTA error",
                enabled = true,
                error = true,
                onClick = {}
            )
            ButtonTextPrimary(
                modifier = Modifier,
                text = "Button CTA error disabled",
                enabled = false,
                error = true,
                onClick = {}
            )
        }
    }
}