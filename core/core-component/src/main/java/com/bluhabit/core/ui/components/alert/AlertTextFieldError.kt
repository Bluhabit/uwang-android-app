/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.alert

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTheme
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun AlertTextFieldError(
    message: String = ""
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.alert_triangle),
            contentDescription = "",
            modifier = androidx.compose.ui.Modifier
                .size(dimens.dp_16)
        )
        Text(
            text = message,
            style = UwangTypography.LabelMedium.Regular,
            color = UwangColors.State.Error.Main
        )
    }
}

@Preview
@Composable
fun PreviewAlertTextFieldError() {
    UwangTheme {
        AlertTextFieldError(
            "Ini adalah error message."
        )
    }
}