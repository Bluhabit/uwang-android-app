/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
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
import com.bluehabit.core.ui.theme.Error200
import com.bluehabit.core.ui.theme.Error600
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Primary200
import com.bluehabit.core.ui.theme.Primary700

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
    error: Boolean = false,
    onClick: () -> Unit = {}
) {
    val ctx = LocalContext.current
    Button(
        modifier = modifier.height(44.dp.from(context = ctx)),
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (error) Error600 else Primary700,
            contentColor = Color(0xFFFFFFFF),
            disabledBackgroundColor = if (error) Error200 else Primary200,
            disabledContentColor = Color(0xFFFFFFFF),
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp
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
fun PreviewButtonPrimary() {
    GaweanTheme {
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
                error = false,
                onClick = {}
            )
            ButtonPrimary(
                modifier = Modifier,
                text = "Button CTA error",
                enabled = true,
                error = true,
                onClick = {}
            )
            ButtonPrimary(
                modifier = Modifier,
                text = "Button CTA error disabled",
                enabled = false,
                error = true,
                onClick = {}
            )
        }
    }
}