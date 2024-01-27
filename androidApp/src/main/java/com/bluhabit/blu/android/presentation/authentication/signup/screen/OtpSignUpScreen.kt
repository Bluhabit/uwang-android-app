/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signup.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluhabit.blu.android.presentation.authentication.signup.SignUpAction
import com.bluhabit.blu.android.presentation.authentication.signup.SignUpState
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.components.textfield.TextFieldOtp
import com.bluhabit.core.ui.theme.UwangTheme
import kotlinx.coroutines.delay

@Composable
fun OtpSignUpScreen(
    modifier: Modifier = Modifier,
    state: SignUpState = SignUpState(),
    onBackPressed: () -> Unit,
    onAction: (SignUpAction) -> Unit
) {
    val focusManager = LocalFocusManager.current
    var timeLeft by remember { mutableStateOf(1 * 60 * 1000L) }

    LaunchedEffect(key1 = timeLeft) {
        while (timeLeft > 0) {
            delay(1000)
            timeLeft -= 1000
        }
        if (timeLeft == 0L) {
            onAction(SignUpAction.OnButtonEnabledChange(false))
        }
    }

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        IconButton(
            onClick = {
                onBackPressed()
            }) {
            Icon(
                painter = painterResource(id = com.bluehabit.core.ui.R.drawable.ic_arrow_back),
                contentDescription = "arrow back"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = stringResource(id = com.bluehabit.core.ui.R.string.send_otp),
                        style = MaterialTheme.typography.body1,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600
                    )
                    Spacer(modifier = modifier.height(12.dp))
                    Text(
                        text = stringResource(id = com.bluehabit.core.ui.R.string.send_otp_p1),
                        style = MaterialTheme.typography.body1,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400
                    )
                    Text(
                        text = stringResource(id = com.bluehabit.core.ui.R.string.send_otp_p3),
                        style = MaterialTheme.typography.body1,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontStyle = MaterialTheme.typography.body1.fontStyle,
                                    fontWeight = FontWeight.W400
                                )
                            ) {
                                append(stringResource(id = com.bluehabit.core.ui.R.string.send_otp_p2))
                            }
                            append(" ")
                            withStyle(
                                style = SpanStyle(
                                    color = Color(0xFF4F504E),
                                    fontStyle = MaterialTheme.typography.body1.fontStyle,
                                    fontWeight = FontWeight.W400
                                )
                            ) {
                                append(state.emailState)
                            }
                        }
                    )
                }
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    TextFieldOtp(
                        modifier = Modifier.align(Alignment.Center),
                        enabled = true,
                        length = 4,
                        value = state.otpState,
                        error = state.otpError,
                        onDone = {
                            focusManager.clearFocus(true)
                        },
                        onChange = { value ->
                            onAction(SignUpAction.OnOtpChange(value))
                        }
                    )

                }
            }
            ButtonPrimary(
                modifier = modifier
                    .fillMaxWidth(),
                text = stringResource(com.bluehabit.core.ui.R.string.send_otp_screen_next),
                enabled = state.otpState.isNotEmpty(),
                onClick = {
                    onAction(SignUpAction.VerifyOtpUpBasic)
                }
            )
        }
    }

}

@Preview
@Composable
fun PreviewOtpScreen() {
    UwangTheme {
        OtpSignUpScreen(
            onAction = {},
            onBackPressed = {}
        )
    }
}