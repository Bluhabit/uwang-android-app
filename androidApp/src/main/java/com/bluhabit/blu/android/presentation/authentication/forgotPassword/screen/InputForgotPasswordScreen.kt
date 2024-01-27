/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.forgotPassword.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.presentation.authentication.forgotPassword.ForgotPasswordAction
import com.bluhabit.blu.android.presentation.authentication.forgotPassword.ForgotPasswordState
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.components.textfield.TextFieldPrimary
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.CustomTypography
import com.bluhabit.core.ui.theme.UwangTheme

@Composable
fun InputForgotPasswordScreen(
    state: ForgotPasswordState = ForgotPasswordState(),
    onBackPressed: () -> Unit,
    onAction: (ForgotPasswordAction) -> Unit = {},
) {

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
                text = stringResource(id = R.string.reset_password_screen_reset_password),
                style = CustomTypography.Body.XL.W600,
                color = UwangColors.Neutral.Grey13
            )
            Text(
                text = stringResource(id = R.string.reset_password_screen_hint),
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
                text = stringResource(id = R.string.reset_password_screen_email),
                style = CustomTypography.Body.Small.W400,
                color = UwangColors.Neutral.Grey9
            )
            TextFieldPrimary(
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text(
                        text = stringResource(id = R.string.reset_password_screen_email_text_field_label),
                        style = CustomTypography.Body.XS.W500
                    )
                },
                value = state.emailState,
                onValueChange = {
                    onAction(ForgotPasswordAction.OnEmailChange(value = it))
                },
                isError = state.emailError
            )
            if (state.emailError) {
                Text(
                    text = state.emailErrorText,
                    style = CustomTypography.Label.Small.W400,
                    color = UwangColors.Error.Red300
                )
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
            onAction(ForgotPasswordAction.ForgotPassword)
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