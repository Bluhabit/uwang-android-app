/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.ui.Grey50

@Composable
fun ButtonGoogle(
    modifier: Modifier = Modifier,
    text: String = "",
    enabled: Boolean = true,
    fullWidth: Boolean = true,
    onClick: () -> Unit = {}
) {
    val properties = if (fullWidth) modifier.fillMaxWidth() else modifier

    Button(
        enabled = enabled,
        onClick = onClick,
        modifier = properties
            .fillMaxWidth()
            .height(45.dp),
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Grey50,
            contentColor = MaterialTheme.colors.onSurface
        )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = text
            )
            Spacer(modifier = modifier.width(6.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.button,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview
@Composable
fun PreviewButtonGoogle() {
    BaseMainApp {
        ButtonGoogle(
            text = "Continue"
        )
    }
}