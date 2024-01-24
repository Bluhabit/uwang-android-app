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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.bluhabit.blu.android.presentation.authentication.signin.SignInState
import com.bluhabit.core.ui.components.alert.CountdownText
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.components.textfield.TextFieldOtp
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.CustomTypography
import com.bluhabit.core.ui.theme.UwangTheme
import kotlinx.coroutines.delay

@Composable
fun OtpSignInScreen(
    state: SignInState = SignInState(),
    onBackPressed: () -> Unit,
    onAction: (SignInAction) -> Unit = {},
) {

    val focusManager = LocalFocusManager.current
    var timeLeft by remember { mutableStateOf(1 * 60 * 1000L) }

    LaunchedEffect(key1 = timeLeft) {
        while (timeLeft > 0) {
            delay(1000)
            timeLeft -= 1000
        }
        if (timeLeft == 0L) {
            onAction(SignInAction.OnButtonEnabledChange(false))
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
    ) {
        IconButton(
            onClick = {
                onBackPressed()
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
                color = UwangColors.Neutral.Grey13
            )
            Text(
                text = stringResource(
                    R.string.otp_sign_in_screen_reset_instruction,
                    "J****11@gmail.com"
                ),
                style = CustomTypography.Body.Small.W400,
                color = UwangColors.Neutral.Grey9
            )
        }
        CountdownText(timeLeft)
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            TextFieldOtp(
                modifier = Modifier.align(Alignment.Center),
                enabled = true,
                length = 4,
                value = state.otpNumberState,
                error = state.otpNumberError,
                onDone = {
                    focusManager.clearFocus(true)
                },
                onChange = { value ->
                    onAction(SignInAction.OnOtpChange(value = value))
                }
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        ButtonPrimary(
            text = stringResource(id = R.string.otp_sign_in_screen_reset_next_button_text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 42.dp),
            enabled = state.otpButtonEnabled
        ) {
            onAction(SignInAction.OnVerifyOtp)
        }
    }
}

@Preview
@Composable
fun OtpSignInScreenPreview() {
    UwangTheme {
        OtpSignInScreen(
            onBackPressed = {}
        )
    }
}