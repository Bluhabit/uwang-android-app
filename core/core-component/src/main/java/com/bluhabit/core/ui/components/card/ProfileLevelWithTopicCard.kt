/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.components.chip.RoundedChip
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun ProfileLevelWithTopicCard(
    modifier: Modifier = Modifier,
    topicList: List<String> = listOf(),
    onTopicChipClicked: (index: Int) -> Unit = {},
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    BaseProfileCard(
        modifier = modifier,
        height = dimens.from(93.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = dimens.dp_12),
            verticalArrangement = Arrangement.spacedBy(dimens.dp_12)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimens.dp_8),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimens.dp_12)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.badge_level_1),
                    contentDescription = "",
                    modifier = Modifier
                        .width(dimens.dp_28)
                        .height(dimens.from(29.3.dp))
                )
                Text(
                    text = stringResource(id = R.string.beginner_1),
                    style = UwangTypography.BodySmall.Medium,
                    color = UwangColors.Text.Main
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "",
                    tint = UwangColors.Text.Secondary,
                    modifier = Modifier
                        .padding(
                            horizontal = dimens.from(9.dp),
                            vertical = dimens.from(6.dp)
                        )
                )
            }
            LazyRow(
                contentPadding = PaddingValues(horizontal = dimens.dp_12),
                horizontalArrangement = Arrangement.spacedBy(dimens.from(4.dp))
            ) {
                itemsIndexed(topicList) { index, item ->
                    RoundedChip(
                        text = item,
                        height = dimens.dp_28,
                        verticalPadding = dimens.from(4.dp),
                        onClick = {
                            onTopicChipClicked(index)
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfileLevelWithTopicCardPreview() {
    val topicList = stringArrayResource(id = R.array.topic_list).copyOfRange(0,3).toList()
    ProfileLevelWithTopicCard(
        modifier = Modifier
            .padding(top = 56.dp, start = 16.dp, end = 16.dp)
            .safeDrawingPadding(),
        topicList = topicList
    )
}