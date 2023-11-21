/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.alert

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.theme.GaweanTheme
import com.bluhabit.core.ui.theme.Success50
import com.bluhabit.core.ui.theme.Success700

@Composable
fun AlertSuccess(
    modifier: Modifier = Modifier,
    message: String,
    onClick: () -> Unit,
) {
    BasicAlert(
        leadingButton = {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_information_circle_success,
                ),
                contentDescription = stringResource(id = R.string.description_icon_success),
            )
        },
        trailingButton = {
            IconButton(
                onClick = {
                    onClick()
                },
                modifier = modifier
                    .size(24.dp)
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_close
                    ),
                    contentDescription = null,
                    tint = Success700,
                    modifier = modifier
                        .size(16.dp)
                )
            }
        },
        message = message,
        messageColor = Success700,
        backgroundColor = Success50,
        borderColor = Success700.copy(0.16f)
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewAlerSuccess() {
    GaweanTheme {
        AlertSuccess(message = "Test alert success") {

        }
    }
}