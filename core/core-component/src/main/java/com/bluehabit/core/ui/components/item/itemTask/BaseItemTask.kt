/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.item.itemTask

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.checkbox.CheckedCircleCheckBox
import com.bluehabit.core.ui.theme.Gray100
import com.bluehabit.core.ui.theme.Gray200

@Composable
fun BaseItemTask(
    modifier: Modifier = Modifier,
    priority: String = "none",
    iconPriorityTint: Color,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {},
    onItemClicked: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(BorderStroke(1.dp, Gray200), RoundedCornerShape(8.dp))
            .clickable(onClick = onItemClicked)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    if (checked) Gray100 else Color.White,
                )
                .padding(20.dp)
        ) {
            CheckedCircleCheckBox(
                checked = checked
            ) {
                onCheckedChange(checked)
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth(0.8f),
            ) {
                content()
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(
                    id = if (priority == "none") {
                        R.drawable.outlined_priority_flag
                    } else {
                        R.drawable.priority_flag
                    }
                ),
                tint = iconPriorityTint,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
}