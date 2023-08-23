/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.item.itemTask

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ProgressItemTask(
    modifier: Modifier = Modifier,
    priority: String = "none",
    iconPriorityTint: Color? = null,
    title: String,
    startDate: String,
    dueDate: String,
    attachmentCount: String,
    subTaskCount: String,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {},
) {
    // TODO
}