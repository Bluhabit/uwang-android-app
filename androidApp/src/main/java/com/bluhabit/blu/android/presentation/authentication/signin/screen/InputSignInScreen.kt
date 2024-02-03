/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signin.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.presentation.authentication.signin.SignInAction
import com.bluhabit.blu.android.presentation.authentication.signin.SignInState
import com.bluhabit.core.ui.components.button.ButtonGoogle
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.components.textfield.TextFieldBase
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTheme
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun InputSignInScreen(
    state: SignInState = SignInState(),
    onSignInGoogle: () -> Unit,
    onSignUp: () -> Unit,
    onForgotPassword: () -> Unit,
    onTermAndCondition: () -> Unit,
    action: (SignInAction) -> Unit
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
            .padding(horizontal = dimens.dp_16, vertical = dimens.dp_24)
            .verticalScroll(rememberScrollState()),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "arrow back",
                modifier = Modifier
                    .size(dimens.dp_24)
                    .align(Alignment.CenterStart)
                    .clickable {
                        // On Back Pressed
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "",
                modifier = Modifier
                    .size(dimens.dp_24)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.padding(bottom = dimens.dp_24))
        Text(
            text = stringResource(id = R.string.title_header_login),
            style = UwangTypography.BodyLarge.SemiBold,
            color = UwangColors.Text.Main
        )
        Spacer(modifier = Modifier.padding(bottom = dimens.dp_16))
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = stringResource(id = R.string.label_field_email),
                style = UwangTypography.BodySmall.Regular,
                color = UwangColors.Palette.Neutral.Grey9
            )
            TextFieldBase(
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text(
                        text = stringResource(id = R.string.placholder_field_email),
                        style = UwangTypography.BodySmall.Regular,
                        color = UwangColors.Palette.Neutral.Grey7
                    )
                },
                value = state.emailState,
                onValueChange = {
                    action(SignInAction.OnEmailChange(value = it))
                },
                isError = state.emailError,
                trailingIcon = {
                    if (state.emailError) {
                        Image(
                            painter = painterResource(id = R.drawable.info),
                            contentDescription = "",
                            modifier = Modifier
                                .size(dimens.dp_16)
                        )
                    }
                }
            )
            if (state.emailError) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.alert_triangle),
                        contentDescription = "",
                        modifier = Modifier
                            .size(dimens.dp_16)
                    )
                    Text(
                        text = state.emailErrorText,
                        style = UwangTypography.LabelMedium.Regular,
                        color = UwangColors.State.Error.Main
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(bottom = dimens.dp_12))
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = "Password",
                style = UwangTypography.BodySmall.Regular,
                color = UwangColors.Palette.Neutral.Grey9
            )
            TextFieldBase(
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text(
                        text = stringResource(id = R.string.placholder_field_password),
                        style = UwangTypography.BodySmall.Regular,
                        color = UwangColors.Palette.Neutral.Grey7
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            action(
                                SignInAction.OnPasswordVisibilityChange(
                                    visibility = !state.passwordVisibility
                                )
                            )
                        }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (!state.passwordVisibility) {
                                    R.drawable.ic_eye_open
                                } else {
                                    R.drawable.ic_eye_closed
                                }
                            ),
                            tint = Color(0xFF0A0A0A),
                            contentDescription = null,
                        )
                    }
                },
                visualTransformation = if (state.passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                value = state.passwordState,
                onValueChange = {
                    action(SignInAction.OnPasswordChange(value = it))
                },
                isError = state.passwordError
            )
            if (state.passwordError) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.alert_triangle),
                        contentDescription = "",
                        modifier = Modifier
                            .size(dimens.dp_16)
                    )
                    Text(
                        text = state.passwordErrorText,
                        style = UwangTypography.LabelMedium.Regular,
                        color = UwangColors.State.Error.Main
                    )
                }

            }
            Spacer(modifier = Modifier.padding(bottom = dimens.dp_24))
            Text(
                text = stringResource(id = R.string.label_forgot_password),
                style = UwangTypography.LabelMedium.Medium,
                color = UwangColors.State.Primary.Main,
                textAlign = TextAlign.Right,
                modifier = Modifier
                    .clickable {
                        onForgotPassword()
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.padding(bottom = dimens.dp_24))
            ButtonPrimary(
                text = stringResource(id = R.string.label_button_login),
                modifier = Modifier
                    .height(44.dp)
                    .fillMaxWidth(),
                enabled = state.signInButtonEnabled
            ) {
                action(SignInAction.OnSignInBasic)
            }
            Spacer(modifier = Modifier.padding(bottom = dimens.dp_24))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    modifier = Modifier
                        .weight(0.9f),
                    color = UwangColors.Text.Separator
                )
                Text(
                    text = stringResource(id = R.string.label_divider),
                    style = UwangTypography.LabelSmall.Regular,
                    color = UwangColors.Text.Secondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(0.4f)
                )
                Divider(
                    modifier = Modifier
                        .weight(0.9f),
                    color = UwangColors.Text.Separator
                )
            }
            Spacer(modifier = Modifier.padding(bottom = dimens.dp_24))
            ButtonGoogle(
                modifier = Modifier
                    .height(44.dp)
                    .fillMaxWidth(),
                text = stringResource(id = R.string.label_button_google),
                onClick = {
                    onSignInGoogle()
                }
            )
            Spacer(modifier = Modifier.padding(bottom = dimens.dp_24))
            Row {
                Text(
                    text = stringResource(id = R.string.placeholder_teks_register),
                    style = UwangTypography.BodySmall.Regular,
                    color = UwangColors.Text.Secondary
                )
                Text(
                    text = stringResource(id = R.string.label_teks_button_register),
                    style = UwangTypography.BodySmall.Medium,
                    color = UwangColors.State.Primary.Main,
                    modifier = Modifier
                        .clickable {
                            onSignUp()
                        }
                )
            }
        }
    }
}

@Preview
@Composable
fun InputSignInScreenPreview() {
    UwangTheme {
        InputSignInScreen(
            onSignInGoogle = { /*TODO*/ },
            onSignUp = { /*TODO*/ },
            onForgotPassword = { /*TODO*/ },
            onTermAndCondition = { /*TODO*/ },
            action = {

            }
        )
    }
}