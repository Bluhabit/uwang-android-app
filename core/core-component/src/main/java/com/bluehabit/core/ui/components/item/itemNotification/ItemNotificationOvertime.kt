/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.item.itemNotification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.button.ButtonOutlinedPrimary
import com.bluehabit.core.ui.theme.Gray600
import com.bluehabit.core.ui.theme.Gray900

@Composable
fun ItemNotificationOvertime(
    modifier: Modifier = Modifier,
    read: Boolean = false,
    title: String,
    date: String,
    message: String,
    onTaskButtonClicked: () -> Unit = {},
    onItemClicked: () -> Unit = {},
) {
    BaseItemNotification(
        modifier = modifier,
        icon = R.drawable.ic_notification_overtime,
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
        ButtonOutlinedPrimary(
            text = stringResource(id = R.string.text_button_item_notification),
            onClick = onTaskButtonClicked,
            modifier = Modifier
                .padding(top = 12.dp)
        )
    }
}

@Preview
@Composable
fun ItemNotificationOvertimePreview() {
    Column {
        ItemNotificationOvertime(
            title = "1 Tugas sampai batas waktunya",
            date = "12 Juli 2023, 09:12",
            message = "Tugas Anda harus segera diselesaikan.",
        )
        ItemNotificationOvertime(
            title = "1 Tugas sampai batas waktunya",
            date = "12 Juli 2023, 09:12",
            message = "Tugas Anda harus segera diselesaikan.",
            read = true
        )
    }
}