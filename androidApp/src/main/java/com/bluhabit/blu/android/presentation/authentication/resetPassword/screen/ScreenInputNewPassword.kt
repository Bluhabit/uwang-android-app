/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.resetPassword.screen

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
import com.bluhabit.blu.android.R
import com.bluhabit.blu.android.presentation.authentication.resetPassword.ForgotPasswordAction
import com.bluhabit.blu.android.presentation.authentication.resetPassword.ForgotPasswordEffect
import com.bluhabit.blu.android.presentation.authentication.resetPassword.ForgotPasswordState
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.theme.CustomColor
import com.bluhabit.core.ui.theme.CustomTypography
import com.bluhabit.core.ui.theme.UwangTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


@Composable
fun InputNewPasswordScreen(
    forgotPasswordState: ForgotPasswordState = ForgotPasswordState(),
    stateFlow: Flow<ForgotPasswordState> = flowOf(),
    effectFlow: Flow<ForgotPasswordEffect> = flowOf(),
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
                // TODO
            }) {
            Icon(
                painter = painterResource(id = com.bluehabit.core.ui.R.drawable.ic_arrow_back),
                contentDescription = "arrow back"
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = com.bluehabit.core.ui.R.string.input_password_screen_reset_password),
                style = CustomTypography.Body.XL.W600,
                color = CustomColor.Neutral.Grey13
            )
            Text(
                text = stringResource(id = com.bluehabit.core.ui.R.string.input_password_screen_hint),
                style = CustomTypography.Body.Small.W400,
                color = CustomColor.Neutral.Grey9
            )
            Text(
                text = stringResource(id = com.bluehabit.core.ui.R.string.input_password_screen_password),
                style = CustomTypography.Body.Small.W400,
                color = CustomColor.Neutral.Grey9
            )
            InputNewPasswordScreen()
            if (forgotPasswordState.emailError) {
                Text(
                    text = forgotPasswordState.emailErrorText,
                    style = CustomTypography.Label.Small.W400,
                    color = CustomColor.Error.Red300
                )
            }
            Text(
                text = stringResource(id = com.bluehabit.core.ui.R.string.input_password_screen_Passrowd_konfirm),
                style = CustomTypography.Body.Small.W400,
                color = CustomColor.Neutral.Grey9
            )
            InputNewPasswordScreen()
            if (forgotPasswordState.emailError) {
                Text(
                    text = forgotPasswordState.emailErrorText,
                    style = CustomTypography.Label.Small.W400,
                    color = CustomColor.Error.Red300
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        ButtonPrimary(
            text = stringResource(id = com.bluehabit.core.ui.R.string.input_password_screen_next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 42.dp),
            enabled = forgotPasswordState.nextButtonEnabled
        ) {
            // Next
        }
    }
}

@Preview
@Composable
fun InputNewPasswordScreenPreview() {
    UwangTheme {
        InputNewPasswordScreen()
    }
}