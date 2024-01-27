/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.pager

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.bluhabit.core.ui.theme.UwangColors

@Composable
fun Indicator(modifier: Modifier = Modifier, isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 24.dp else 12.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = ""
    )
    Box(
        modifier = modifier
            .height(4.dp)
            .width(width.value)
            .clip(RoundedCornerShape(3.dp))
            .background(
                color =
                if (isSelected) UwangColors.Primary.Blue500 else UwangColors.Primary.Blue500.copy(
                    alpha = 0.5f
                )
            )
    ) {

    }
}

@Composable
fun BoxScope.Indicators(m: Modifier = Modifier, size: Int, index: Int) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = m
            .align(Alignment.Center)
            .fillMaxWidth(),
    ) {
        repeat(size) {
            Spacer(modifier = Modifier.width(12.dp))
            Indicator(isSelected = it == index)
        }
    }

}