/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluhabit.core.ui.components.alert.CountdownTimerScreen
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.components.textfield.TextFieldOtp
import com.bluhabit.core.ui.theme.UwangTheme

@Composable
fun ScreenOtp(
    modifier: Modifier = Modifier,
    state: SignUpState = SignUpState(),
    onChange: (String) -> Unit = {},
    onSubmit: () -> Unit = {}
) {
    Scaffold(
        bottomBar = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 10.dp,
                        vertical = 10.dp
                    )
            ) {
                ButtonPrimary(
                    modifier = modifier
                        .fillMaxWidth(),
                    text = stringResource(com.bluehabit.core.ui.R.string.send_otp_screen_next),
                    enabled = state.otp.isNotEmpty(),
                    onClick = onSubmit
                )
            }
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
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
                modifier = modifier
                    .padding(it)
                    .fillMaxWidth()
                    .padding(
                        vertical = 20.dp,
                        horizontal = 16.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
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
                    Text(text = buildAnnotatedString {
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
                            append(state.email)
                        }
                        Spacer(modifier = modifier.height(2.dp))
                        CountdownTimerScreen()
                    })
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    TextFieldOtp(
                        onDone = onSubmit
                    )

                }
                Column {

                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewOtpScreen() {
    UwangTheme {
            ScreenOtp()
    }
}