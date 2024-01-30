/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.onboard.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.components.button.ButtonGoogle
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.theme.CustomTypography
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTheme
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun GetStartedScreen(
    modifier: Modifier = Modifier,
    onSignInGoogle: () -> Unit = {},
    onSignUp: () -> Unit = {},
    onNavigationToSignIn: () -> Unit = {},
    onNavigateToTermCondition: () -> Unit = {},
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
            .safeDrawingPadding()
            .padding(
                top = dimens.dp_24,
                start = dimens.dp_16,
                end = dimens.dp_16,
                bottom = dimens.dp_24
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
           modifier = Modifier
               .border(
                   width = dimens.from(2.dp),
                   brush = Brush.linearGradient(
                       colors = listOf(
                           Color(0xFF000AF9),
                           Color(0xFF836FFF),
                           Color(0xFF16E1C8),
                       )
                   ),
                   shape = CircleShape
               )
               .padding(2.dp)
               .size(dimens.from(96.dp)),

        ){
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "",
                modifier = Modifier
                    .size(dimens.from(86.dp))
                    .clip(CircleShape)
                    .align(Alignment.Center)
            )
        }

        Text(
            text = stringResource(id = R.string.text_get_started),
            style = UwangTypography.DisplayMedium.SemiBold.copy(
                fontSize = 30.sp
            ),
            color = UwangColors.Text.Heading,

            )
        Column(
            verticalArrangement = Arrangement.Bottom
        ) {
            ButtonGoogle(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.label_button_google),
                onClick = onSignInGoogle
            )
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 18.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    modifier = Modifier.weight(0.9f)
                )
                Text(
                    text = stringResource(id = R.string.label_divider),
                    style = CustomTypography.Body.Small.W400,
                    color = UwangColors.Neutral.Grey8,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(0.4f)
                )
                Divider(
                    modifier = Modifier.weight(0.9f)
                )
            }
            ButtonPrimary(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                text = stringResource(id = R.string.label_teks_button_register),
                onClick = onSignUp
            )
            Column {
                Text(
                    text = stringResource(id = R.string.text_term_conditions_1),
                    style = UwangTypography.BodySmall.Regular,
                    color = UwangColors.Text.Secondary
                )
                Row() {
                    Text(text = stringResource(id = R.string.text_term_conditions_2),
                        style = UwangTypography.BodySmall.Regular,
                        color = UwangColors.State.Primary.Main,
                        modifier = Modifier.clickable {
                            onNavigateToTermCondition()
                        })
                    Spacer(modifier = Modifier.padding(horizontal = dimens.from(2.dp)))
                    Text(
                        text = stringResource(id = R.string.text_term_conditions_3),
                        style = UwangTypography.BodySmall.Regular,
                        color = UwangColors.Text.Secondary
                    )
                }
            }
            Spacer(modifier = Modifier.padding(horizontal = dimens.from(40.dp)))
            Row() {
                Text(
                    text = stringResource(id = R.string.placeholder_teks_login),
                    style = UwangTypography.BodySmall.Regular,
                    color = UwangColors.Text.Secondary
                )
                Spacer(modifier = Modifier.padding(horizontal = dimens.from(2.dp)))
                Text(text = stringResource(id = R.string.text_button_daftar),
                    style = UwangTypography.BodySmall.Regular,
                    color = UwangColors.State.Primary.Main,
                    modifier = Modifier.clickable {
                        onNavigationToSignIn()
                    })
            }
        }
    }
}

@Preview
@Composable
fun GetStartedScreenPreview() {
    UwangTheme {
        GetStartedScreen()
    }
}