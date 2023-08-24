/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.changePassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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

@Composable
fun ScreenConfirmChangePassword(
    modifier: Modifier = Modifier,
    state: ChangePasswordState = ChangePasswordState(),
    onPasswordChanged: (String) -> Unit = {},
    onPasswordConfirmationChanged: (String) -> Unit = {},
    onResetPassword: () -> Unit = {}
) {
    val context = LocalContext.current

    fun isValid(): Boolean {
        return (state.password == state.confirmPassword) && (state.password.isNotEmpty() &&
                state.confirmPassword.isNotEmpty())
    }

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

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.text_label_change_password_profile),
                style = MaterialTheme.typography.h6,
                lineHeight = 30.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center,
                color = Gray900
            )
            Text(
                text = stringResource(id = R.string.text_subtitle_confirm_password),
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
                onChange = onPasswordChanged,
                error = state.passwordError,
                errorMessage = state.passwordErrorMessage
            )
            InputPasswordPrimary(
                label = stringResource(id = R.string.text_label_input_confirmation_new_password_screen_auth),
                placeholder = stringResource(id = R.string.text_placeholder_input_confirmation_new_password_screen_auth),
                value = state.confirmPassword,
                onChange = onPasswordConfirmationChanged,
                error = state.confirmPasswordError,
                errorMessage = state.confirmPasswordErrorMessage
            )
        }
        ButtonPrimary(
            modifier = modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.text_button_create_new_password_screen_auth),
            enabled = isValid(),
            onClick = onResetPassword,
        )
    }
}

@Preview
@Composable
fun PreviewInputNewPassword() {
    var state by remember {
        mutableStateOf(ChangePasswordState())
    }
    GaweanTheme {
        ScreenConfirmChangePassword(
            state = state,
            onPasswordChanged = {
                state = state.copy(password = it)
            },
            onPasswordConfirmationChanged = {
                state = state.copy(confirmPassword = it)
            },
        )
    }
}