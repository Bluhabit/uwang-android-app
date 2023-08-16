/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.resetPassword.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trian.mvi.ui.extensions.from
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray900
import com.bluehabit.core.ui.theme.Primary600
import com.bluehabit.eureka.feature.authentication.R

@Composable
fun InstructionResetScreen(
    onOpenEmailApp: () -> Unit = {},
    onRetry: () -> Unit = {},
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(
                vertical = 28.dp.from(context = context),
                horizontal = 26.dp.from(context = context),
            )
            .background(Color.White),

        verticalArrangement = Arrangement.spacedBy(95.dp.from(context = context)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = 28.dp.from(context = context),
                ),
            verticalArrangement = Arrangement.spacedBy(24.dp.from(context = context)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = com.bluehabit.core.ui.R.drawable.gawean_logo),
                contentDescription = "logo"
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp.from(context = context)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = com.bluehabit.core.ui.R.string.text_title_instruction_send),
                    style = MaterialTheme.typography.h6,
                    lineHeight = 30.sp,
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Center,
                    color = Gray900
                )
                Text(
                    text = stringResource(id = com.bluehabit.core.ui.R.string.text_subtitle_instruction),
                    style = MaterialTheme.typography.body2,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center,
                    color = Gray900
                )
            }
            Column(
                Modifier.padding(30.dp)
            ) {
                Image(
                    painter = painterResource(id = com.bluehabit.core.ui.R.drawable.hero_intruction_reset_password),
                    contentDescription = "hero image insctruction screen"
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp.from(context = context)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ButtonPrimary(
                modifier = Modifier
                    .width(320.dp.from(context = context))
                    .height(48.dp.from(context = context)),
                text = stringResource(R.string.text_open_email),
                enabled = true,
                onClick = onOpenEmailApp
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp.from(context = context)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = com.bluehabit.core.ui.R.string.text_check_spam),
                    style = MaterialTheme.typography.caption,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center,
                    color = Gray900
                )
                Text(
                    text = stringResource(id = com.bluehabit.core.ui.R.string.text_or),
                    style = MaterialTheme.typography.caption,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center,
                    color = Gray900
                )
                Text(
                    modifier = Modifier.clickable {
                        onRetry()
                    },
                    text = stringResource(id = com.bluehabit.core.ui.R.string.text_try_again),
                    style = MaterialTheme.typography.body2,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center,
                    color = Primary600
                )
            }
        }
    }
}

@Composable
@Preview(widthDp = 384, heightDp = 854)
fun PreviewInstructionScreen() {
    GaweanTheme(darkTheme = false) {
        InstructionResetScreen()
    }
}