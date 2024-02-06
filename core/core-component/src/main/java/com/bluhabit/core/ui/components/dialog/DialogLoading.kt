/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangTheme

@Composable
fun DialogLoading(
    modifier: Modifier = Modifier,
    show: Boolean = false
) {
    if (show) {
        Dialog(onDismissRequest = {}) {
            Box(
                modifier = modifier
                    .background(Color.Transparent)
                    .size(200.dp)
            ) {
                CircularProgressIndicator(
                    modifier = modifier.align(Alignment.Center),
                    strokeCap = StrokeCap.Round,
                    strokeWidth = 4.dp,
                    color = UwangColors.Base.White,
                    backgroundColor = Color.Gray.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Preview
@Composable
fun DialogLoadingPreview() {
    UwangTheme {
        DialogLoading(
            show = true,
        )
    }
}
