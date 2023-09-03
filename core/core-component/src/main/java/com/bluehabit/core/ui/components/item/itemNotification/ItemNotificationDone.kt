/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.item.itemNotification

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.Gray600
import com.bluehabit.core.ui.theme.Gray900

@Composable
fun ItemNotificationDone(
    modifier: Modifier = Modifier,
    read: Boolean = false,
    title: String,
    message: String,
    date: String,
    onItemClicked: () -> Unit = {},
) {
    BaseItemNotification(
        modifier = modifier,
        icon = R.drawable.ic_notification_done,
        read = read,
        onItemClicked = onItemClicked,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
            lineHeight = 18.sp,
            fontWeight = FontWeight.W500,
            color = Gray900,
        )
        Text(
            text = date,
            style = MaterialTheme.typography.caption,
            lineHeight = 18.sp,
            fontWeight = FontWeight.W400,
            color = Gray600,
        )
        Text(
            text = message,
            style = MaterialTheme.typography.body2,
            lineHeight = 18.sp,
            fontWeight = FontWeight.W400,
            color = Gray900,
        )
    }
}

@Preview
@Composable
fun ItemNotificationDonePreview() {
    Column {
        ItemNotificationDone(
            title = "1 Tugas selesai",
            message = "Anda telah menyelesaikan 1 tugas",
            date = "12 Juli 2023, 09:12",
        )
        ItemNotificationDone(
            title = "1 Tugas selesai",
            message = "Anda telah menyelesaikan 1 tugas",
            date = "12 Juli 2023, 09:12",
            read = true
        )
    }
}