/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signin.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.presentation.authentication.signin.SignInAction
import com.bluhabit.blu.android.presentation.authentication.signin.SignInEffect
import com.bluhabit.blu.android.presentation.authentication.signin.SignInState
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.components.textfield.TextFieldOtp
import com.bluhabit.core.ui.theme.CustomColor
import com.bluhabit.core.ui.theme.CustomTypography
import com.bluhabit.core.ui.theme.UwangTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun OtpSignInScreen(
    signInState: SignInState = SignInState(),
    stateFlow: Flow<SignInState> = flowOf(),
    effectFlow: Flow<SignInEffect> = flowOf(),
    onAction: (SignInAction) -> Unit = {},
) {
    val focusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
    ) {
        IconButton(
            onClick = {
                // On Back Pressed
            }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "arrow back",
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.otp_sign_in_screen_reset_title),
                style = CustomTypography.Body.XL.W600,
                color = CustomColor.Neutral.Grey13
            )
            Text(
                text = stringResource(
                    R.string.otp_sign_in_screen_reset_instruction,
                    "J****11@gmail.com"
                ),
                style = CustomTypography.Body.Small.W400,
                color = CustomColor.Neutral.Grey9
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 7.dp)
        ) {
            Text(
                text = stringResource(id = R.string.otp_sign_in_screen_reset_confirmation_time),
                style = CustomTypography.Body.XS.W500,
                color = CustomColor.Neutral.Grey13
            )
            Text(
                text = "00.57",
                style = CustomTypography.Body.XS.W500,
                color = CustomColor.Error.Red500
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            TextFieldOtp(
                modifier = Modifier.align(Alignment.Center),
                enabled = true,
                length = 4,
                value = signInState.otpNumberState,
                error = signInState.otpNumberError,
                onDone = {
                    focusManager.clearFocus(true)
                },
                onChange = { value ->
                    onAction(SignInAction.OtpNumberAction(value = value))
                }
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        ButtonPrimary(
            text = stringResource(id = R.string.otp_sign_in_screen_reset_next_button_text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 42.dp),
            enabled = signInState.otpButtonEnabled
        ) {
            // Next
        }
    }
}

@Preview
@Composable
fun OtpSignInScreenPreview() {
    UwangTheme {
        OtpSignInScreen()
    }
}