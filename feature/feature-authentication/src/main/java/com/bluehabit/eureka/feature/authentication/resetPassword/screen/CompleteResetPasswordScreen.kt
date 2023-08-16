/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.resetPassword.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.extensions.from
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.theme.GaweanTheme

@Composable
fun CompleteResetPasswordScreen(
    modifier: Modifier = Modifier
) {
    val ctx = LocalContext.current
    Column(
        modifier = modifier
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
                text = stringResource(id = R.string.text_title_complete_reset_password),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.W600
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.text_subtitle_complete_reset_password),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = modifier.height(40.dp))
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.hero_complete_reset_password),
                contentDescription = ""
            )
            Spacer(modifier = modifier.height(45.dp))
            ButtonPrimary(
                modifier = modifier.fillMaxWidth(),
                text = "Masuk"
            )
        }
        Column {

        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewCompleteResetPasswordScreen() {
    GaweanTheme {
        CompleteResetPasswordScreen()
    }
}