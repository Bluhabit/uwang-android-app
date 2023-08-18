/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.resetPassword.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trian.mvi.ui.extensions.from
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.components.input.InputPasswordPrimary
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray900
import com.bluehabit.eureka.feature.authentication.resetPassword.ResetPasswordState

@Composable
fun ScreenInputNewPassword(
    modifier: Modifier = Modifier,
    state: ResetPasswordState = ResetPasswordState(),
    onPasswordChanged: (String) -> Unit = {},
    onPasswordConfirmationChanged: (String) -> Unit = {},
    onResetPassword: () -> Unit = {}
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(
                vertical = 24.dp.from(context = context),
                horizontal = 26.dp.from(context = context),
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.gawean_logo),
            contentDescription = stringResource(id = R.string.content_description_logo),
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.text_title_create_new_password_screen_auth),
                style = MaterialTheme.typography.h6,
                lineHeight = 30.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center,
                color = Gray900
            )
            Text(
                text = stringResource(id = R.string.text_description_create_new_password_screen_auth),
                style = MaterialTheme.typography.body2,
                lineHeight = 20.sp,
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Center,
                color = Gray900
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = modifier
                .fillMaxWidth()
        ) {
            InputPasswordPrimary(
                label = stringResource(id = R.string.text_label_input_new_password_screen_auth),
                placeholder = stringResource(id = R.string.text_placeholder_input_new_password_screen_auth),
                value = state.password,
                onChange = onPasswordChanged
            )
            InputPasswordPrimary(
                label = stringResource(id = R.string.text_label_input_confirmation_new_password_screen_auth),
                placeholder = stringResource(id = R.string.text_placeholder_input_confirmation_new_password_screen_auth),
                value = state.passwordConfirmation,
                onChange = onPasswordConfirmationChanged
            )
        }
        ButtonPrimary(
            modifier = modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.text_button_create_new_password_screen_auth),
            enabled = state.password.isNotEmpty() && state.passwordConfirmation.isNotEmpty(),
            onClick = onResetPassword
        )
    }
}

@Preview
@Composable
fun PreviewInputNewPassword() {
    GaweanTheme {
        ScreenInputNewPassword()
    }

}