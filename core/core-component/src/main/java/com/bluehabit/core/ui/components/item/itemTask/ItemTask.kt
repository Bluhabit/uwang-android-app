/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.item.itemTask

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import com.bluehabit.core.ui.theme.Gray500
import com.bluehabit.core.ui.theme.Gray900

@Composable
fun ItemTask(
    modifier: Modifier = Modifier,
    priority: String = "none",
    iconPriorityTint: Color? = null,
    title: String,
    date: String,
    attachmentCount: String,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {},
) {
    BaseItemTask(
        modifier = modifier,
        checked = checked,
        priority = priority,
        iconPriorityTint = iconPriorityTint,
        onCheckedChange = {
            onCheckedChange(it)
        }
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
            text = attachmentCount
        )
    }
}

@Preview
@Composable
fun ItemTaskPreview() {
    var checked by remember {
        mutableStateOf(false)
    }
    ItemTask(
        title = "Competitor Analys",
        priority = "none",
        iconPriorityTint = null,
        date = "07 Agu 2023 ",
        attachmentCount = "3",
        checked = checked,
        modifier = Modifier
            .padding(12.dp)
    ) {
        checked = !checked
    }
}