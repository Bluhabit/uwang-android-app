/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trian.mvi.ui.extensions.Empty
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Primary600

@Composable
fun ButtonLogout(
    modifier: Modifier = Modifier,
    label: String = String.Empty,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(start = 20.dp, top = 16.dp, end = 20.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Icon(
            painter = painterResource(
                id = R.drawable.logout
            ),
            tint = Primary600,
            contentDescription = "",
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 14.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF101828),
            )
        )
    }
}

@Composable
@Preview(widthDp = 500, heightDp = 750)
fun PreviewButtonLogout() {
    GaweanTheme()
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ButtonLogout(
                label = stringResource(id = R.string.text_button_logout),
                onClick = {}
            )
        }
    }
}