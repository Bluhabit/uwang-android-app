/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.personalization.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.bluhabit.core.ui.components.textfield.TextFieldPrimary
import com.bluhabit.core.ui.components.textfield.TextFieldState
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTheme
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun CreateUsernameScreen(
    modifier: Modifier = Modifier,
    state: PersonalizationState = PersonalizationState(),
    onAction: (PersonalizationAction) -> Unit = {},
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(UwangColors.Base.White)
            .safeDrawingPadding(),
    ) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    start = dimens.dp_16, end = dimens.dp_16,
                    top = dimens.dp_24, bottom = dimens.from(82.dp + 32.dp) // 82 (bottom nav size) + 32
                ),
        ) {
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
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.title_header_username),
                    style = UwangTypography.BodyXL.SemiBold,
                    color = UwangColors.Text.Main
                )
                Text(
                    text = stringResource(id = R.string.desctiption_header_username),
                    style = UwangTypography.BodySmall.Regular,
                    color = UwangColors.Text.Secondary
                )
            }
            Spacer(modifier = modifier.padding(bottom = dimens.dp_16))
            TextFieldPrimary(
                modifier = modifier
                    .fillMaxWidth(),
                label = stringResource(id = R.string.label_field_username),
                placeholder = stringResource(id = R.string.placeholder_field_username),
                value = state.usernameValueState,
                onValueChange = {
                    onAction(PersonalizationAction.OnUsernameChange(it))
                },
                state = state.usernameState,
                leadingIcon = {
                    Text(
                        text = "@",
                        style = UwangTypography.BodySmall.Regular,
                        color = UwangColors.Text.Main
                    )
                }
            )
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
                    onClick = {onAction(PersonalizationAction.NextSkip)}
                )
                ButtonPrimary(
                    modifier = modifier
                        .width(dimens.from(102.dp))
                        .height(dimens.from(36.dp)),
                    text = stringResource(id = R.string.label_button_next),
                    enabled = state.usernameValueState.isNotEmpty()
                            && state.usernameState !is TextFieldState.Error,
                    onClick = {
                        onAction(PersonalizationAction.CreateUsernameNextButton)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun CreateUsernameScreenPreview() {
    UwangTheme {
        CreateUsernameScreen()
    }
}