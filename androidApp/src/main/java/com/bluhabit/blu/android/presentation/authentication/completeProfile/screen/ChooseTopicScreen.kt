/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.completeProfile.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.presentation.authentication.completeProfile.CompleteProfileAction
import com.bluhabit.blu.android.presentation.authentication.completeProfile.CompleteProfileState
import com.bluhabit.core.ui.components.button.ButtonOutlinedPrimary
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.components.chip.RoundedChip
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTheme
import com.bluhabit.core.ui.theme.UwangTypography

data class SelectedTopic(
    val topic: String,
    val selectedTime: Long,
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChooseTopicScreen(
    state: CompleteProfileState = CompleteProfileState(),
    onAction: (CompleteProfileAction) -> Unit = {},
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    val topicList = listOf(
        stringResource(id = R.string.label_tag_topic_satu),
        stringResource(id = R.string.label_tag_topic_dua),
        stringResource(id = R.string.label_tag_topic_tiga),
        stringResource(id = R.string.label_tag_topic_empat),
        stringResource(id = R.string.label_tag_topic_lima),
        stringResource(id = R.string.label_tag_topic_enam),
        stringResource(id = R.string.label_tag_topic_tujuh),
        stringResource(id = R.string.label_tag_topic_delapan),
        stringResource(id = R.string.label_tag_topic_sembilan),
        stringResource(id = R.string.label_tag_topic_sepuluh),
        stringResource(id = R.string.label_tag_topic_sebelas),
        stringResource(id = R.string.label_tag_topic_duabelas),
        stringResource(id = R.string.label_tag_topic_tigabelas),
        stringResource(id = R.string.label_tag_topic_empatbelas),
        stringResource(id = R.string.label_tag_topic_limabelas),
        stringResource(id = R.string.label_tag_topic_enambelas),
        stringResource(id = R.string.label_tag_topic_tujuhbelas),
        stringResource(id = R.string.label_tag_topic_delapanbelas),
        stringResource(id = R.string.label_tag_topic_sembilanbelas),
        stringResource(id = R.string.label_tag_topic_duapuluh),
        stringResource(id = R.string.label_tag_topic_duapuluhsatu),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(UwangColors.Base.White)
            .safeDrawingPadding(),
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    start = dimens.dp_16, end = dimens.dp_16,
                    top = dimens.dp_24, bottom = dimens.from(82.dp + 32.dp) // 82 (bottom nav size) + 32
                ),
            verticalArrangement = Arrangement.spacedBy(dimens.dp_16)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(dimens.dp_24)
                        .align(Alignment.Center)
                )

            }
            Spacer(modifier = Modifier.padding(bottom = dimens.dp_8)) // 16 + 8
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.title_header_topic),
                    style = UwangTypography.BodyXL.SemiBold,
                    color = UwangColors.Text.Main
                )
                Text(
                    text = stringResource(id = R.string.description_header_topic),
                    style = UwangTypography.BodySmall.Regular,
                    color = UwangColors.Text.Secondary
                )
            }
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(dimens.dp_8),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                state.selectedTopicList
                    .sortedBy { it.selectedTime }
                    .forEach {
                        RoundedChip(
                            text = it.topic,
                            selected = true,
                            onClick = {
                                onAction(CompleteProfileAction.OnRemoveSelectedList(it))
                            }
                        )
                    }
            }
            if (state.selectedTopicList.isNotEmpty()) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.placeholder_count_topic, state.selectedTopicList.size.toString()),
                        style = UwangTypography.BodyXS.Regular,
                        color = UwangColors.Text.Secondary
                    )
                    Text(
                        text = stringResource(id = R.string.label_text_button_reset),
                        style = UwangTypography.BodyXS.Medium,
                        color = UwangColors.State.Primary.Main,
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .clickable {
                                onAction(CompleteProfileAction.OnClearSelectedList)
                            }
                    )
                }
                Divider(color = UwangColors.Text.Border)
            }
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(dimens.dp_8),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                topicList
                    .filter {
                        state.selectedTopicList.find { selectedTopic ->
                            selectedTopic.topic == it
                        } == null
                    }
                    .forEach {
                        RoundedChip(
                            text = it,
                            selected = false,
                            onClick = {
                                onAction(
                                    CompleteProfileAction.OnAddSelectedList(
                                        SelectedTopic(
                                            topic = it,
                                            selectedTime = System.currentTimeMillis(),
                                        )
                                    )
                                )
                            }
                        )
                    }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(UwangColors.Base.White)
                .align(Alignment.BottomCenter),
        ) {
            Divider(color = UwangColors.Text.Border)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimens.dp_16, vertical = dimens.dp_24)
            ) {
                ButtonOutlinedPrimary(
                    modifier = Modifier
                        .width(dimens.from(102.dp))
                        .height(dimens.from(36.dp)),
                    text = stringResource(id = R.string.label_button_pass)
                )
                ButtonPrimary(
                    modifier = Modifier
                        .width(dimens.from(102.dp))
                        .height(dimens.from(36.dp)),
                    text = stringResource(id = R.string.label_button_next),
                    enabled = state.selectedTopicList.isNotEmpty() && state.chooseTopicScreenNextButtonEnabled
                )
            }
        }
    }
}

@Preview
@Composable
fun ChooseTopicScreenPreview() {
    UwangTheme {
        ChooseTopicScreen()
    }
}