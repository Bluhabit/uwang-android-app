/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.signUp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.extensions.Empty
import app.trian.mvi.ui.extensions.from
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.components.input.InputOtp
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Primary600
import com.bluehabit.core.ui.theme.Primary700

@Composable
fun OtpScreen(
    modifier: Modifier = Modifier,
    otp: String = String.Empty,
    email: String = String.Empty,
    onChange: (String) -> Unit = {}
) {
    val ctx = LocalContext.current
    Scaffold(
        bottomBar = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 10.dp
                    )
            ) {
                ButtonPrimary(
                    modifier = modifier
                        .fillMaxWidth(),
                    text = stringResource(R.string.btn_confirmation_otp_screen),
                    enabled = false
                )
            }
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
                .background(
                    MaterialTheme.colors.surface
                )
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
                Image(
                    painter = painterResource(id = R.drawable.gawean_logo),
                    contentDescription = stringResource(id = R.string.content_description_logo),
                    modifier = modifier.height(34.dp.from(context = ctx))
                )
                Spacer(modifier = modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.text_title_otp_screen),
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.W600
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = stringResource(id = R.string.text_subtitle2_otp_screen),
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.W400
                )
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontStyle = MaterialTheme.typography.body1.fontStyle,
                            fontWeight = FontWeight.W400
                        )
                    ) {
                        append(stringResource(id = R.string.text_subtitle3_otp_screen))
                    }
                    append(" ")
                    withStyle(
                        style = SpanStyle(
                            color = Primary700,
                            fontStyle = MaterialTheme.typography.body1.fontStyle,
                            fontWeight = FontWeight.W400
                        )
                    ) {
                        append(email)
                    }
                })
                Spacer(modifier = modifier.height(20.dp))
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                InputOtp(
                    otp = otp,
                    onChange = { otp, valid ->
                        if (valid) {
                            onChange(otp)
                        }
                    }
                )
                Spacer(modifier = modifier.height(20.dp))
                Text(
                    text = stringResource(id = R.string.text_resend_otp),
                    color = Primary600
                )
            }
            Column {

            }
        }
    }
}

@Preview
@Composable
fun PreviewOtpScreen() {
    var otp by remember {
        mutableStateOf("")
    }
    GaweanTheme {
        OtpScreen(
            otp = otp,
            email = "example@gmail.com",
            onChange = {
                otp = it
            }
        )
    }
}