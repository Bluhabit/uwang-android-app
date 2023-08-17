/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.auth.screen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.bluehabit.core.ui.components.button.ButtonFacebook
import com.bluehabit.core.ui.components.button.ButtonGoogle
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.components.input.InputPasswordPrimary
import com.bluehabit.core.ui.components.input.InputTextPrimary
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray200
import com.bluehabit.core.ui.theme.Gray300
import com.bluehabit.core.ui.theme.Gray700
import com.bluehabit.core.ui.theme.Gray900
import com.bluehabit.core.ui.theme.Primary25
import com.bluehabit.core.ui.theme.Primary600
import com.bluehabit.core.ui.theme.Primary700
import com.bluehabit.eureka.feature.authentication.auth.AuthState

@Composable
fun ScreenSignIn(
    modifier: Modifier = Modifier,
    state: AuthState = AuthState(),
    onEmailChanged: (String) -> Unit = {},
    onPasswordChanged: (String) -> Unit = {},
    onRememberChecked: (Boolean) -> Unit = {},
    onNavigateToResetPassword: () -> Unit = {},
    onSignInGoogle:()->Unit={},
    onSignInEmail:()->Unit={},
    onSignInFacebook:()->Unit={},
    onShowTermCondition:()->Unit={},
    onShowPrivacyPolicy:()->Unit={}
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary25)
            .padding(
                horizontal = 18.dp
            ),
    ) {
        InputTextPrimary(
            label = stringResource(id = R.string.text_label_input_email_screen_auth),
            placeholder = stringResource(id = R.string.text_placeholder_input_email_screen_auth),
            value = state.emailSignIn,
            onChange = onEmailChanged,
            enabled = true,
        )
        Spacer(modifier = Modifier.height(16.dp))
        InputPasswordPrimary(
            label = stringResource(id = R.string.text_label_input_password_screen_auth),
            placeholder = stringResource(id = R.string.text_placeholder_input_password_screen_auth),
            value = state.passwordSignIn,
            onChange = onPasswordChanged,
            enabled = true,
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
                Checkbox(
                    checked = state.isRememberChecked,
                    onCheckedChange = onRememberChecked,
                    enabled = true,
                    colors = CheckboxDefaults.colors(
                        checkedColor = Primary700,
                        uncheckedColor = Gray300,
                        disabledColor = Gray200
                    ),
                    modifier = modifier.clip(RoundedCornerShape(4.dp))
                )
                Text(
                    text = stringResource(id = R.string.text_label_remember_me_screen_auth),
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.W500,
                    color = Gray700,
                )
            }
            Text(
                text = stringResource(id = R.string.text_forgot_password_screen_auth),
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.W500,
                color = Primary600,
                modifier = modifier.clickable {
                    onNavigateToResetPassword()
                }
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
                        vertical = 10.dp
                    ),
                text = stringResource(id = R.string.text_button_login_screen_auth),
                enabled = state.emailSignIn.isNotEmpty() && state.passwordSignIn.isNotEmpty(),
                onClick = onSignInEmail
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
                ButtonGoogle(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.text_button_signin_with_google_screen_auth),
                    enabled = true,
                    onClick = onSignInGoogle
                )
                ButtonFacebook(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    text = stringResource(id = R.string.text_button_register_screen_auth),
                    enabled = true,
                    onClick = onSignInFacebook
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Gray900,
                                fontStyle = MaterialTheme.typography.body1.fontStyle,
                                fontWeight = FontWeight.W400
                            )
                        ) {
                            append(stringResource(id = R.string.text_term_and_condition_title_screen_auth))
                        }
                        append(" ")
                        withStyle(
                            style = SpanStyle(
                                color = Primary700,
                                fontStyle = MaterialTheme.typography.body1.fontStyle,
                                fontWeight = FontWeight.W400
                            )
                        ) {
                            append(stringResource(id = R.string.text_term_and_condition_screen_auth))
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Gray900,
                                fontStyle = MaterialTheme.typography.body1.fontStyle,
                                fontWeight = FontWeight.W400
                            )
                        ) {
                            append(" ")
                            append(stringResource(id = R.string.text_and_screen_auth))
                            append(" ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Primary700,
                                fontStyle = MaterialTheme.typography.body1.fontStyle,
                                fontWeight = FontWeight.W400
                            )
                        ) {
                            append(stringResource(id = R.string.text_privacy_police_screen_auth))
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
fun PreviewScreenSignIn() {
    GaweanTheme {
        ScreenSignIn()
    }
}