/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.alert

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
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
fun AlertSuccess(
    modifier: Modifier = Modifier,
    message: String,
    onClick: () -> Unit,
) {
    BasicAlert(
        modifier = modifier,
        leadingButton = {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_success_alert,
                ),
                contentDescription = stringResource(id = R.string.description_icon_success),
                modifier = Modifier
                    .size(24.dp)
            )
        },
        trailingButton = {
            Icon(
                painter = painterResource(
                    id = R.drawable.ic_close
                ),
                contentDescription = null,
                tint = UwangColors.Text.Main,
                modifier = Modifier
                    .size(16.dp)
                    .clickable(onClick = onClick)
            )
        },
        message = message,
        messageColor = UwangColors.State.Success.Main,
        backgroundColor = UwangColors.State.Success.Surface,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewAlertSuccess() {
    UwangTheme {
        AlertSuccess(
            modifier = Modifier
                .padding(16.dp),
            message = "Test alert success"
        ) {

        }
    }
}