/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.auth.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trian.mvi.ui.extensions.from
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.components.button.ButtonSocial
import com.bluehabit.core.ui.components.input.InputTextPrimary
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray700
import com.bluehabit.core.ui.theme.Gray900
import com.bluehabit.core.ui.theme.Primary25
import com.bluehabit.core.ui.theme.Primary600
import com.bluehabit.core.ui.theme.Primary700

@Composable
fun ScreenSignUp(
    isEmailValid: Boolean = true,
    isPasswordValid: Boolean = true,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Primary25),
        verticalArrangement = Arrangement.spacedBy(20.dp.from(context = context), alignment = Alignment.Top)
    ) {
        var emailInput by remember {
            mutableStateOf("")
        }

        InputTextPrimary(
            label = "Email",
            value = emailInput,
            onChange = { emailInput = it },
            eror = !isEmailValid,
            enable = true,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val isChecked = remember { mutableStateOf(false) }

                Checkbox(
                    checked = isChecked.value,
                    onCheckedChange = { isChecked.value = it },
                    enabled = true,
                    colors = CheckboxDefaults.colors(Primary600)
                )
                Text(
                    text = stringResource(com.bluehabit.eureka.feature.authentication.R.string.remember_me),
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.W500,
                    color = Gray700,
                )
            }
            Text(
                text = stringResource(com.bluehabit.eureka.feature.authentication.R.string.forget_password),
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.W500,
                color = Primary600,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ButtonPrimary(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 18.dp,
                        vertical = 10.dp
                    ),
                text = "Masuk"
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp.from(context = context), Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.line_4),
                    contentDescription = "line"
                )
                Text(
                    text = stringResource(R.string.text_or),
                    style = MaterialTheme.typography.caption,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.W400,
                    color = Primary600,
                )
                Image(
                    painter = painterResource(id = R.drawable.line_4),
                    contentDescription = "line"
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ButtonSocial(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp,
                            vertical = 10.dp
                        ),
                    text = "Masuk Dengan Google",
                    enabled = true,
                    icon = {
                        Image(
                            painterResource(
                                id = R.drawable.social_icon_google
                            ),
                            contentDescription = "social icon"
                        )
                    },
                    backgroundColor = Color.White,
                    textColor = Gray700,
                    onClick = {}
                )
                ButtonSocial(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp,
                            vertical = 10.dp
                        ),
                    text = "Masuk Dengan Facebook",
                    enabled = true,
                    icon = {
                        Image(
                            painterResource(
                                id = R.drawable.social_icon_facebook
                            ),
                            contentDescription = "social icon"
                        )
                    },
                    backgroundColor = Color(0xFF1877F2),
                    textColor = Color.White,
                    onClick = {}
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Gray900,
                                fontStyle = MaterialTheme.typography.body1.fontStyle,
                                fontWeight = FontWeight.W400
                            )
                        ) {
                            append("Dengan masuk ke akun berarti Anda telah menyetujui")
                        }
                        append(" ")
                        withStyle(
                            style = SpanStyle(
                                color = Primary700,
                                fontStyle = MaterialTheme.typography.body1.fontStyle,
                                fontWeight = FontWeight.W400
                            )
                        ) {
                            append("Syarat & Ketentuan")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Gray900,
                                fontStyle = MaterialTheme.typography.body1.fontStyle,
                                fontWeight = FontWeight.W400
                            )
                        ) {
                            append(" dan ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Primary700,
                                fontStyle = MaterialTheme.typography.body1.fontStyle,
                                fontWeight = FontWeight.W400
                            )
                        ) {
                            append(" Kebijakan Privasi yang berlaku.")
                        }
                    },
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}

@Preview
@Composable
fun PreviewScreenSignUp() {
    GaweanTheme {
        ScreenSignUp()
    }
}