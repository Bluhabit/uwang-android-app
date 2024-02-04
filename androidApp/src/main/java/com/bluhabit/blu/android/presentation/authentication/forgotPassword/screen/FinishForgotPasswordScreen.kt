/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.forgotPassword.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangTheme
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun FinishForgotPasswordScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(UwangColors.Base.White)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_bg_complete_sign_up
                ),
                contentDescription = ""
            )
            Text(
                text = stringResource(id = R.string.reset_password_title_complete_forgot),
                style = UwangTypography.BodyXL.SemiBold,
                color = UwangColors.Text.Main
            )
            Text(
                text = stringResource(id = R.string.reset_password_subtitle_complete_forgot),
                style = UwangTypography.BodySmall.Regular,
                color = UwangColors.Text.Main
            )
        }
        Row(
            modifier = modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 18.dp)
        ) {
            ButtonPrimary(
                text = "Masuk",
                modifier = modifier
                    .fillMaxWidth(),
                enabled = true,
                onClick = {
                    onClick()
                }
            )
        }
    }

}

@Preview
@Composable
fun FinishForgotPasswordScreenPreview() {
    UwangTheme {
        FinishForgotPasswordScreen()
    }
}