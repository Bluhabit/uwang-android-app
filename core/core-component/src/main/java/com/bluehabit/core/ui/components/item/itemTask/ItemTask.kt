/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.item.itemTask

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.label.AttachmentLabel
import com.bluehabit.core.ui.theme.Error500
import com.bluehabit.core.ui.theme.Gray500
import com.bluehabit.core.ui.theme.Gray900
import com.bluehabit.core.ui.theme.Warning400

@Composable
fun ItemTask(
    modifier: Modifier = Modifier,
    priority: String = "none",
    iconPriorityTint: Color,
    title: String,
    date: String,
    attachmentCount: Int = 0,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {},
    onItemClicked: () -> Unit = {},
) {
    BaseItemTask(
        modifier = modifier,
        checked = checked,
        priority = priority,
        iconPriorityTint = iconPriorityTint,
        onCheckedChange = {
            onCheckedChange(it)
        },
        onItemClicked = onItemClicked,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
            textDecoration = if (checked) TextDecoration.LineThrough else TextDecoration.None,
            lineHeight = 18.sp,
            fontWeight = FontWeight.W500,
            color = Gray900,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_calendar),
                tint = Gray500,
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
            )
            Text(
                text = date,
                style = MaterialTheme.typography.body2,
                lineHeight = 18.sp,
                fontWeight = FontWeight.W400,
                color = Gray500,
            )
        }
        AttachmentLabel(
            text = attachmentCount.toString()
        )
    }
}

@Preview
@Composable
fun ItemTaskPreview() {
    var checked by remember {
        mutableStateOf(false)
    }
    var checked2 by remember {
        mutableStateOf(false)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        ItemTask(
            title = "Competitor Analys",
            priority = "none",
            iconPriorityTint = Error500,
            date = "07 Agu 2023 ",
            attachmentCount = 3,
            checked = checked,
            onCheckedChange = {
                checked = !it
            }
        ) {
            //Do Something
        }
        ItemTask(
            title = "Competitor Analys",
            priority = "high",
            iconPriorityTint = Warning400,
            date = "07 Agu 2023 ",
            attachmentCount = 3,
            checked = checked2,
            onCheckedChange = {
                checked2 = !it
            }
        ) {
            //Do Something
        }
    }
}