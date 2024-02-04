/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.forgotPassword.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.bluhabit.core.ui.components.textfield.TextFieldState
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
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
            .padding(horizontal = dimens.dp_16, vertical = dimens.dp_24),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "arrow back",
                    modifier = Modifier
                        .size(dimens.dp_24)
                        .align(Alignment.CenterStart)
                        .clickable {
                            onBackPressed()
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(dimens.dp_24)
                        .align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.padding(bottom = dimens.dp_24))
            Text(
                text = stringResource(id = R.string.reset_password_title_header),
                style = UwangTypography.BodyLarge.SemiBold,
                color = UwangColors.Text.Main
            )
            Spacer(modifier = Modifier.padding(bottom = 4.dp))
            Text(
                text = stringResource(id = R.string.reset_password_description_header),
                style = UwangTypography.BodySmall.Regular,
                color = UwangColors.Text.Secondary
            )
            Spacer(modifier = Modifier.padding(bottom = dimens.dp_16))
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                TextFieldPrimary(
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = stringResource(id = R.string.reset_password_input_label_email),
                    placeholder = stringResource(id = R.string.reset_password_input_placeholder_email),
                    value = state.emailState,
                    onValueChange = {
                        action(ForgotPasswordAction.OnEmailChange(it))
                    },
                    state = state.emailInputState,
                )
            }
            Spacer(modifier = Modifier.padding(bottom = dimens.dp_12))
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(dimens.dp_24),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ButtonPrimary(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.reset_password_screen_next),
                enabled = state.emailInputState !is TextFieldState.Error
                        && state.emailState.isNotEmpty()
            ) {
                action(ForgotPasswordAction.RequestResetPassword)
            }
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