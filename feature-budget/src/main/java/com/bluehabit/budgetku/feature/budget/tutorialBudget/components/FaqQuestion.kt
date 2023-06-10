/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.tutorialBudget.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.Blue50
import com.bluehabit.core.ui.theme.Blue800

@Composable
internal fun FaqQuestion(
    isExpand: Boolean = false,
    title: String = "",
    onExpanded: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topEnd = 16.dp,
                    topStart = 16.dp,
                    bottomStart = if (isExpand) 0.dp else 16.dp,
                    bottomEnd = if (isExpand) 0.dp else 16.dp,
                )
            )
            .height(52.dp)
            .background(Blue50)
            .clickable {
                onExpanded(isExpand)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1,
            color = Blue800,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 18.dp, top = 16.dp, bottom = 16.dp),
        )
        Icon(
            painter = painterResource(id = if (isExpand) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down),
            contentDescription = "Up Down",
            tint = Blue800,
            modifier = Modifier
                .padding(end = 16.dp)
                .height(6.dp)
                .width(12.dp),
        )
    }
}