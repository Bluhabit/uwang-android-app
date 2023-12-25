/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.checkbox

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
import com.bluhabit.core.ui.theme.CustomColor
import com.bluhabit.core.ui.theme.Gray100
import com.bluhabit.core.ui.theme.Gray200
import com.bluhabit.core.ui.theme.Gray300

@Composable
fun BaseCircleCheckbox(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    checked: Boolean = true,
    backgroundColor: Color = CustomColor.Primary.Blue500,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onCheckedChange: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit = {},
) {
    Box(
        modifier = modifier
            .size(24.dp)
            .background(
                if (checked) backgroundColor
                else if (!enabled) Gray100
                else Color.White,
                CircleShape
            )
            .clip(CircleShape)
            .border(
                if (checked) 0.dp else 2.dp,
                if (checked) Gray300 else Gray200,
                CircleShape
            )
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