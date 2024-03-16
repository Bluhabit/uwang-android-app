/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.personalization.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.presentation.authentication.personalization.PersonalizationAction
import com.bluhabit.blu.android.presentation.authentication.personalization.PersonalizationState
import com.bluhabit.core.ui.components.button.ButtonOutlinedPrimary
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.components.checkbox.ItemLevelCheckBox
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTheme
import com.bluhabit.core.ui.theme.UwangTypography

data class ItemLevel(
    val value: String = "",
    val image: Int,
    val title: String,
    val description: String,
)

@Composable
fun ChooseLevelScreen(
    modifier: Modifier = Modifier,
    state: PersonalizationState = PersonalizationState(),
    onAction: (PersonalizationAction) -> Unit = {},
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    val levelList = listOf(
        ItemLevel(
            value = "INTERMEDIATE",
            image = R.drawable.image_level_1,
            title = stringResource(id = R.string.title_card_beginner),
            description = stringResource(id = R.string.description_card_beginner)
        ),
        ItemLevel(
            value = "PRO",
            image = R.drawable.image_level_2,
            title = stringResource(id = R.string.title_card_intermediate),
            description = stringResource(id = R.string.description_card_Intermediate)
        ),
        ItemLevel(
            value = "ADVANCE",
            image = R.drawable.image_level_3,
            title = stringResource(id = R.string.title_card_expert),
            description = stringResource(id = R.string.description_card_expert)
        ),
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(UwangColors.Base.White)
            .safeDrawingPadding(),
    ) {
        LazyColumn(
            modifier = modifier
                .padding(bottom = dimens.from(72.dp)),
            contentPadding = PaddingValues(vertical = dimens.dp_24),
            verticalArrangement = Arrangement.spacedBy(dimens.dp_16)
        ) {
            item {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.app_logo),
                        contentDescription = "",
                        modifier = modifier
                            .size(dimens.dp_24)
                            .align(Alignment.Center)
                    )

                }
                Spacer(modifier = modifier.padding(bottom = dimens.dp_24))
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = modifier
                        .padding(horizontal = dimens.dp_16)
                ) {
                    Text(
                        text = stringResource(id = R.string.title_header_level),
                        style = UwangTypography.BodyXL.SemiBold,
                        color = UwangColors.Text.Main
                    )
                    Text(
                        text = stringResource(id = R.string.description_header_level),
                        style = UwangTypography.BodySmall.Regular,
                        color = UwangColors.Text.Secondary
                    )
                }
                Spacer(modifier = modifier.padding(bottom = dimens.dp_8)) // 16 + 8 = 24
            }
            items(levelList) { item ->
                ItemLevelCheckBox(
                    modifier = modifier
                        .padding(horizontal = dimens.dp_16),
                    image = { Image(painter = painterResource(id = item.image), contentDescription = "") },
                    title = item.title,
                    description = item.description,
                    checked = state.selectedLevel == item.value,
                    onCheckedChange = {
                        onAction(PersonalizationAction.OnSelectedLevelChange(item.value))
                    }
                )
            }
        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(UwangColors.Base.White)
                .align(Alignment.BottomCenter),
        ) {
            Divider(color = UwangColors.Text.Border)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimens.dp_16, vertical = dimens.dp_24)
            ) {
                ButtonOutlinedPrimary(
                    modifier = modifier
                        .width(dimens.from(102.dp))
                        .height(dimens.from(36.dp)),
                    text = stringResource(id = R.string.label_button_pass),
                    borderColor = UwangColors.State.Primary.Main,
                    onClick = {onAction(PersonalizationAction.NextSkip)}
                )
                ButtonPrimary(
                    modifier = modifier
                        .width(dimens.from(102.dp))
                        .height(dimens.from(36.dp)),
                    text = stringResource(id = R.string.label_button_next),
                    enabled = state.selectedLevel.isNotEmpty(),
                    // Adjustment to match the size of the outline button
                    border = BorderStroke(
                        width = 1.dp,
                        color = when {
                            state.selectedLevel.isNotEmpty() -> UwangColors.State.Primary.Main
                            else -> UwangColors.Palette.Neutral.Grey3
                        }
                    )
                ) {
                    onAction(PersonalizationAction.ChooseLevelNextButton)
                }
            }
        }
    }
}

@Preview
@Composable
fun ChooseLevelScreenPreview() {
    UwangTheme {
        ChooseLevelScreen(
            state = PersonalizationState(
                selectedLevel = "INTERMEDIATE"
            )
        )
    }
}