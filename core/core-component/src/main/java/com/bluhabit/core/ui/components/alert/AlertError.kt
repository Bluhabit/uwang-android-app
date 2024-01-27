/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.alert

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
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
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangTheme

@Composable
fun AlertError(
    modifier: Modifier = Modifier,
    message: String,
    onClick: () -> Unit,
) {
    BasicAlert(
        modifier = modifier,
        leadingButton = {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_error_alert,
                ),
                contentDescription = stringResource(id = R.string.description_icon_error),
            )
        },
        trailingButton = {
            IconButton(
                onClick = {
                    onClick()
                },
                modifier = Modifier
                    .size(24.dp)
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_close
                    ),
                    contentDescription = null,
                    tint = UwangColors.Text.Main,
                    modifier = Modifier
                        .size(16.dp)
                )
            }
        },
        message = message,
        messageColor = UwangColors.State.Error.Main,
        backgroundColor = UwangColors.Palette.Error.Red1,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewAlertError() {
    UwangTheme {
        AlertError(
            modifier = Modifier
                .padding(16.dp),
            message = "Test alert error"
        ) {

        }
    }
}