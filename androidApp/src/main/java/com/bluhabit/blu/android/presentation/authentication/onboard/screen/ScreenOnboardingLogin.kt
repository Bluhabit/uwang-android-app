/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.components.button.ButtonEmailLogin
import com.bluhabit.core.ui.components.button.ButtonGoogle
import com.bluhabit.core.ui.theme.CustomColor
import com.bluhabit.core.ui.theme.CustomTypography
import com.bluhabit.core.ui.theme.UwangTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun OnboardLogin(
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ) {
            Text(
                text = stringResource(id = R.string.onboard_screen_login),
                style = CustomTypography.Body.XL.W600,
                fontSize = 20.sp,
                color = Color(0xFF1041B7),
            )
            Spacer(modifier = Modifier.padding(bottom = 16.dp))
            Text(
                text = stringResource(id = R.string.onboard_screen_login_title),
                fontSize = 14.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF939392),
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.padding(bottom = 35.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.onboard4),
                contentDescription = "",
                modifier = Modifier
                    .padding(0.dp)
                    .width(320.dp)
                    .height(186.33945.dp)
            )
            Spacer(modifier = Modifier.padding(bottom = 120.dp))
            ButtonGoogle(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = stringResource(id = R.string.sign_in_screen_sign_in_google_button_text),
            ) {
                // Sign in
            }
            Spacer(modifier = Modifier.padding(bottom = 4.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    modifier = Modifier
                        .weight(0.9f)
                )
                Text(
                    text = stringResource(id = R.string.sign_in_screen_or),
                    style = CustomTypography.Body.Small.W400,
                    color = CustomColor.Neutral.Grey8,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(0.4f)
                )
                Divider(
                    modifier = Modifier
                        .weight(0.9f)
                )
            }
            Spacer(modifier = Modifier.padding(bottom = 4.dp))
            ButtonEmailLogin(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = stringResource(id = R.string.sign_in_screen_login_email_button_text),
            ) {
                // Sign in
            }
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp),
        ) {
            Text(
                text = stringResource(id = R.string.sign_in_screen_not_have_an_account),
                style = CustomTypography.Body.Small.W400,
                color = CustomColor.Neutral.Grey9
            )
            Text(
                text = stringResource(id = R.string.sign_in_screen_register),
                style = CustomTypography.Body.Small.W400,
                color = CustomColor.Primary.Blue500,
                modifier = Modifier
                    .clickable {
                        //On register clicked
                    }
            )

        }
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = stringResource(id = R.string.sign_in_screen_term_and_condition_1),
                style = CustomTypography.Body.Small.W400,
                color = CustomColor.Neutral.Grey7
            )
            Text(
                text = stringResource(id = R.string.sign_in_screen_term_and_condition_2),
                style = CustomTypography.Body.Small.W400,
                color = CustomColor.Primary.Blue500,
                modifier = Modifier
                    .clickable {
                        //On terms and condition clicked
                    }
            )
        }
    }
}

@Preview
@Composable
fun OnboardLoginPreview() {
    UwangTheme {
        OnboardLogin()
    }
}
