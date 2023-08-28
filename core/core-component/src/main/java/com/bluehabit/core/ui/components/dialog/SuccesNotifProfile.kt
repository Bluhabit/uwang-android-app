/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.Success600

@Composable
fun NotificatioBarSuccess(
    notificationText: String
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Success600),
        elevation = 4.dp,
        color = Success600
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.check
                ),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = notificationText,
                color = Color.White,
                fontSize = 16.sp,
            )
        }
    }
}

@Preview
@Composable
fun NotificationBarPreview() {
    NotificatioBarSuccess(notificationText = stringResource(id = R.string.text_label_notif_profile))
}