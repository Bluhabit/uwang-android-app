/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.forgotPassword.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
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
import com.bluhabit.blu.android.presentation.authentication.forgotPassword.ForgotPasswordAction
import com.bluhabit.blu.android.presentation.authentication.forgotPassword.ForgotPasswordState
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.components.textfield.TextFieldPrimary
import com.bluhabit.core.ui.theme.CustomTypography
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTheme
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun InputForgotPasswordScreen(
    state: ForgotPasswordState = ForgotPasswordState(),
    onBackPressed: () -> Unit,
    action: (ForgotPasswordAction) -> Unit = {},
) {
    val ctx= LocalContext.current
    val dimens = UwangDimens.from(ctx)
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState()),
    ) {
        IconButton(
            onClick = {
                onBackPressed()
            }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "arrow back"
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.reset_password_title_header),
                style = CustomTypography.Body.XL.W600,
                color = UwangColors.Neutral.Grey13
            )
            Text(
                text = stringResource(id = R.string.reset_password_description_header),
                style = CustomTypography.Body.Small.W400,
                color = UwangColors.Neutral.Grey9
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.reset_password_input_label_email),
                style = CustomTypography.Body.Small.W400,
                color = UwangColors.Neutral.Grey9
            )
            TextFieldPrimary(
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text(
                        text = stringResource(id = R.string.placholder_field_email),
                        style = UwangTypography.BodySmall.Regular,
                        color = UwangColors.Palette.Neutral.Grey7
                    )
                },
                value = state.emailState,
                onValueChange = {
                    action(ForgotPasswordAction.OnEmailChange(value = it))
                },
                isError = state.emailError,
                trailingIcon = {
                    if (state.emailError) {
                        Image(
                            painter = painterResource(id = R.drawable.info),
                            contentDescription = "",
                            modifier = Modifier
                                .size(dimens.dp_16)
                        )
                    }
                }
            )
            if (state.emailError) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.alert_triangle),
                        contentDescription = "",
                        modifier = Modifier
                            .size(dimens.dp_16)
                    )
                    Text(
                        text = state.emailErrorText,
                        style = UwangTypography.LabelMedium.Regular,
                        color = UwangColors.State.Error.Main
                    )
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        ButtonPrimary(
            text = stringResource(id = R.string.reset_password_screen_next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 42.dp),
            enabled = state.nextButtonEnabled
        ) {
            action(ForgotPasswordAction.ForgotPassword)
        }
    }
}

@Preview
@Composable
fun ForgotPasswordScreenPreview() {
    UwangTheme {
        InputForgotPasswordScreen(
            onBackPressed = {}
        )
    }
}