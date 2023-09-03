/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.item.itemNotification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.theme.Primary50

@Composable
fun BaseItemNotification(
    modifier: Modifier = Modifier,
    read: Boolean,
    icon: Int,
    onItemClicked: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .background(
                if (read) Color.White else Primary50
            )
            .clickable {
                onItemClicked()
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "",
                modifier = Modifier
                    .size(32.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth(0.8f),
            ) {
                content()
            }
        }
        Divider(
            thickness = 0.5.dp
        )
    }
}