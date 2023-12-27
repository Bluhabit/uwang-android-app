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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.presentation.authentication.completeProfile.CompleteProfileAction
import com.bluhabit.blu.android.presentation.authentication.completeProfile.CompleteProfileState
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.components.checkbox.PreferenceItemCheckBox
import com.bluhabit.core.ui.theme.CustomColor
import com.bluhabit.core.ui.theme.CustomTypography
import com.bluhabit.core.ui.theme.UwangTheme

data class PreferenceItem(
    val checked: Boolean = false,
    val title: String,
    val image: Int,
)

@Composable
fun SetPreferenceScreen(
    state: CompleteProfileState = CompleteProfileState(),
    onBackPressed:()->Unit,
    onAction: (CompleteProfileAction) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        LazyVerticalGrid(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            item(
                span = {
                    GridItemSpan(2)
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "arrow back",
                        modifier = Modifier
                            .clickable {
                                onBackPressed()
                            }
                    )
                }
            }
            item(
                span = {
                    GridItemSpan(2)
                }
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(bottom = 13.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.set_preference_screen_title),
                        style = CustomTypography.Body.XL.W600,
                        color = CustomColor.Neutral.Grey13
                    )
                    Text(
                        text = stringResource(id = R.string.set_preference_screen_instruction),
                        style = CustomTypography.Body.Small.W400,
                        color = CustomColor.Neutral.Grey9
                    )
                }
            }
            itemsIndexed(state.preferenceItems) { index, item ->
                PreferenceItemCheckBox(
                    title = item.title,
                    image = {
                        Image(
                            painter = painterResource(id = item.image),
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize()
                        )
                    },
                    checked = item.checked,
                    onCheckedChange = {
                        onAction(
                            CompleteProfileAction.SetPreferenceScreenPreferenceItem(
                                checked = !item.checked,
                                index = index
                            )
                        )
                    }
                )
            }
        }
        ButtonPrimary(
            text = stringResource(id = R.string.set_preference_screen_next_button_text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 28.dp)
                .align(Alignment.BottomCenter),
            enabled = true
        ) {
            onAction(CompleteProfileAction.NextStep)
        }
    }
}

@Preview
@Composable
fun SetPreferenceScreenPreview() {
    UwangTheme {
        SetPreferenceScreen(
            onBackPressed = {}
        )
    }
}