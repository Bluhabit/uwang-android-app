/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.notifikasi.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trian.mvi.ui.extensions.Empty
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray900

@Composable
fun EmptyNotificationScreen(
    icon: (@Composable () -> Unit)? = null,
    message: String = String.Empty,
    label: String = String.Empty,
    title: String = String.Empty,
    modifier: Modifier = Modifier,
    onSendRequest: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color(0xFFFFFFFF)),
    ) {
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "Arrow Back"
                )
            }
            Text(
                text = label,
                style = MaterialTheme.typography.h6,
                lineHeight = 30.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Start,
                color = Gray900
            )
        }
        Spacer(modifier = Modifier.height(60.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            icon?.invoke()
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    8.dp,
                    Alignment.Top
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6,
                    lineHeight = 30.sp,
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Center,
                    color = Gray900
                )
                Text(
                    text = message,
                    style = MaterialTheme.typography.body2,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center,
                    color = Gray900,
                )
                Spacer(modifier = Modifier.height(10.dp))
                ButtonPrimary(
                    modifier = modifier
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.text_button_notifikasi),
                    enabled = true,
                    onClick = onSendRequest
                )
            }
        }
    }
}
@Composable
@Preview(widthDp = 500, heightDp = 750)
fun PreviewEmptyNotification() {
    GaweanTheme(
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            EmptyNotificationScreen(
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.emptynotif),
                        contentDescription = "",
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                label = stringResource(id = R.string.text_title_nav_notifikasi),
                title = stringResource(id = R.string.text_label_nav_notifikasi),
                message = stringResource(id = R.string.text_placeholder_notifikasi),
            )
        }
    }
}