/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.item.itemTaskFavorite

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trian.mvi.ui.extensions.Empty
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.label.AttachmentLabel
import com.bluehabit.core.ui.components.label.SubTaskLabel
import com.bluehabit.core.ui.theme.Gray100
import com.bluehabit.core.ui.theme.Gray200
import com.bluehabit.core.ui.theme.Gray400
import com.bluehabit.core.ui.theme.Gray500
import com.bluehabit.core.ui.theme.Gray900
import com.bluehabit.core.ui.theme.Primary600
import com.bluehabit.core.ui.theme.Warning300

@Composable
fun ItemTaskFavorite(
    modifier: Modifier = Modifier,
    title: String = String.Empty,
    priority: String = "none",
    iconPriorityTint: Color = Gray400,
    subTaskCount: Int = 0,
    attachmentCount: Int = 0,
    progressTask: Float = 0f,
    date: String = String.Empty,
    onItemClicked: () -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .border(BorderStroke(1.dp, Gray200), RoundedCornerShape(8.dp))
            .clickable(onClick = onItemClicked)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .width(248.dp)
                .height(168.dp)
                .padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = stringResource(id = R.string.description_icon_star),
                    tint = Warning300
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.W500,
                    color = Gray900,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
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

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                LinearProgressIndicator(
                    progress = progressTask,
                    color = Primary600,
                    backgroundColor = Gray100,
                    modifier = Modifier
                        .height(12.dp)
                        .weight(0.5f)
                        .clip(RoundedCornerShape(25.dp)),
                )
                Text(
                    text = "${(progressTask * 100).toInt()}%",
                    style = MaterialTheme.typography.body2,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.W400,
                    color = Gray500,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SubTaskLabel(
                    text = subTaskCount.toString()
                )
                AttachmentLabel(
                    text = attachmentCount.toString()
                )
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
}

@Preview
@Composable
fun ItemTaskFavoritePreview() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        item {
            ItemTaskFavorite(
                title = "Write \"Fauzan is the most handsome people in the world\" 100x using UPPERCASE",
                priority = "high",
                iconPriorityTint = Color.Red,
                subTaskCount = 3,
                attachmentCount = 2,
                progressTask = 1f,
                date = "14 Agu 2023 - 16 Agu 2023",
            )
            ItemTaskFavorite(
                title = "Write \"Fauzan\" 100x",
                priority = "high",
                iconPriorityTint = Color.Red,
                subTaskCount = 3,
                attachmentCount = 2,
                progressTask = 1f,
                date = "14 Agu 2023 - 16 Agu 2023",
            )
        }
    }

}