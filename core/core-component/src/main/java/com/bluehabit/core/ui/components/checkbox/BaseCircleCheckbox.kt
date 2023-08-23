/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.checkbox

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.theme.Gray300
import com.bluehabit.core.ui.theme.Primary600

@Composable
fun BaseCircleCheckbox(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    checked: Boolean = true,
    backgroundColor: Color = Primary600,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onCheckedChange: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit = {},
) {
    val getBackgroundColor = if (checked) backgroundColor else Color.White
    val borderSize = if (checked) 0.dp else 2.dp
    Box(
        modifier = modifier
            .size(32.dp)
            .background(getBackgroundColor, CircleShape)
            .clip(CircleShape)
            .border(borderSize, Gray300, CircleShape)
            .clickable(
                onClick = onCheckedChange,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = rememberRipple()
            )
            .padding(4.dp),

        ) {
        if (checked) {
            content()
        }
    }
}