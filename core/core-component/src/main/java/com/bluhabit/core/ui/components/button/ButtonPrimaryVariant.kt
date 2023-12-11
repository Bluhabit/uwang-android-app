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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluhabit.core.ui.ext.Empty
import com.bluhabit.core.ui.theme.Error100
import com.bluhabit.core.ui.theme.Error25
import com.bluhabit.core.ui.theme.Error300
import com.bluhabit.core.ui.theme.Error700
import com.bluhabit.core.ui.theme.Primary100
import com.bluhabit.core.ui.theme.Primary25
import com.bluhabit.core.ui.theme.Primary300
import com.bluhabit.core.ui.theme.Primary700
import com.bluhabit.core.ui.theme.UwangTheme

/**
 * Button Primary
 * @param modifier
 *
 * @sample PreviewButtonPrimaryVariant
 * */

@Composable
fun ButtonPrimaryVariant(
    modifier: Modifier = Modifier,
    text: String = String.Empty,
    enabled: Boolean = true,
    error: Boolean = false,
    onClick: () -> Unit = {}
) {
    val ctx = LocalContext.current
    Button(
        modifier = modifier.height(44.dp),
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (error) Error100 else Primary100,
            contentColor = if (error) Error700 else Primary700,
            disabledBackgroundColor = if (error) Error25 else Primary25,
            disabledContentColor = if (error) Error300 else Primary300
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
fun PreviewButtonPrimaryVariant() {
    UwangTheme {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
        ) {
            ButtonPrimaryVariant(
                modifier = Modifier,
                text = "Button CTA",
                enabled = true,
                onClick = {}
            )
            ButtonPrimaryVariant(
                modifier = Modifier,
                text = "Button CTA disabled",
                enabled = false,
                onClick = {}
            )
            ButtonPrimaryVariant(
                modifier = Modifier,
                text = "Button CTA error",
                enabled = true,
                error = true,
                onClick = {}
            )
            ButtonPrimaryVariant(
                modifier = Modifier,
                text = "Button CTA error disabled",
                enabled = false,
                error = true,
                onClick = {}
            )
        }
    }
}