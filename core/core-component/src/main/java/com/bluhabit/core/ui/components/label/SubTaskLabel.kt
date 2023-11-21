/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.label

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.theme.Gray500

@Composable
fun SubTaskLabel(
    modifier: Modifier = Modifier,
    text: String,
) {
    BaseIconLabel(
        text = text
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_subtask),
            tint = Gray500,
            contentDescription = null,
            modifier = modifier
                .size(16.dp)
        )
    }
}