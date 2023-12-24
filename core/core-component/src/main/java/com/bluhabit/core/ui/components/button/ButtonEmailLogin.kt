/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.ext.Empty
import com.bluhabit.core.ui.theme.CustomColor
import com.bluhabit.core.ui.theme.UwangTheme

@Composable
fun ButtonEmailLogin(
    modifier: Modifier = Modifier,
    text: String = String.Empty,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    ButtonSocial(
        modifier = modifier,
        text = text,
        enabled = enabled,
        backgroundColor = CustomColor.Neutral.Grey1,
        textColor = CustomColor.Primary.Blue500,
        onClick = onClick
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, heightDp = 500)
@Composable
fun PreviewButtonEmailLogin() {
    UwangTheme {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 10.dp
                )
        ) {
            ButtonEmailLogin(
                modifier = Modifier,
                text = "Masuk Dengan Google",
                enabled = true,
                onClick = {}
            )
            ButtonEmailLogin(
                modifier = Modifier,
                text = "Masuk Dengan Google",
                enabled = false,
                onClick = {}
            )
        }
    }
}