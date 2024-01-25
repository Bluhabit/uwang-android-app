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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.bluhabit.core.ui.components.textfield.TextFieldPrimary
import com.bluhabit.core.ui.theme.CustomTypography
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
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
        verticalArrangement = Arrangement.spacedBy(dimens.dp_16),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
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
                        // TODO: On Back Pressed
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
        Text(
            text = stringResource(id = R.string.title_header_login),
            style = UwangTypography.BodyLarge.SemiBold,
            color = UwangColors.Neutral.Grey13
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = "Email",
                style = UwangTypography.BodyLarge.Regular,
                color = UwangColors.Neutral.Grey9
            )
            TextFieldPrimary(
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text(
                        text = "Masukkan email kamu",
                        style = UwangTypography.BodyLarge.Regular,
                        color = UwangColors.Neutral.Grey7
                    )
                },
                value = state.emailState,
                onValueChange = {
                    action(SignInAction.OnEmailChange(value = it))
                },
                error = state.emailError
            )
            Text(
                text = state.emailErrorText,
                style = CustomTypography.Label.Small.W400,
                color = UwangColors.Error.Red300
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = stringResource(id = R.string.sign_in_screen_password_text_field_title),
                style = CustomTypography.Body.Small.W400,
                color = UwangColors.Neutral.Grey9
            )
            TextFieldPrimary(
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text(text = stringResource(id = R.string.sign_in_screen_password_text_field_label))
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
                            tint = UwangColors.Neutral.Grey7,
                            contentDescription = null,
                        )
                    }
                },
                visualTransformation = if (state.passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                value = state.passwordState,
                onValueChange = {
                    action(SignInAction.OnPasswordChange(value = it))
                },
                error = state.passwordError
            )
            Text(
                text = state.passwordErrorText,
                style = CustomTypography.Label.Small.W400,
                color = UwangColors.Error.Red300
            )

        }
        Text(
            text = stringResource(id = R.string.sign_in_screen_forgot_password),
            style = CustomTypography.Body.XS.W500,
            textAlign = TextAlign.Right,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable {
                    onForgotPassword()
                }
        )
        ButtonPrimary(
            text = stringResource(id = R.string.sign_in_screen_sign_in_button_text),
            modifier = Modifier
                .fillMaxWidth(),
            enabled = state.buttonEnabled
        ) {
            action(SignInAction.OnSignInBasic)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                modifier = Modifier
                    .weight(0.9f)
            )
            Text(
                text = stringResource(id = R.string.sign_in_screen_or),
                style = CustomTypography.Body.Small.W400,
                color = UwangColors.Neutral.Grey8,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(0.4f)
            )
            Divider(
                modifier = Modifier
                    .weight(0.9f)
            )
        }
        ButtonGoogle(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.sign_in_screen_sign_in_google_button_text),
            onClick = {
                onSignInGoogle()
            }
        )
        Row {
            Text(
                text = stringResource(id = R.string.sign_in_screen_not_have_an_account),
                style = CustomTypography.Body.Small.W400,
                color = UwangColors.Neutral.Grey9
            )
            Text(
                text = stringResource(id = R.string.sign_in_screen_register),
                style = CustomTypography.Body.Small.W400,
                color = UwangColors.Primary.Blue500,
                modifier = Modifier
                    .clickable {
                        onSignUp()
                    }
            )
        }
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        Column {
            Text(
                text = stringResource(id = R.string.sign_in_screen_term_and_condition_1),
                style = CustomTypography.Body.Small.W400,
                color = UwangColors.Neutral.Grey7
            )
            Text(
                text = stringResource(id = R.string.sign_in_screen_term_and_condition_2),
                style = CustomTypography.Body.Small.W400,
                color = UwangColors.Primary.Blue500,
                modifier = Modifier
                    .clickable {
                        onTermAndCondition()
                    }
            )
        }
    }
}

@Preview
@Composable
fun InputSignInScreenPreview() {
    InputSignInScreen(
        onSignInGoogle = { /*TODO*/ },
        onSignUp = { /*TODO*/ },
        onForgotPassword = { /*TODO*/ },
        onTermAndCondition = { /*TODO*/ },
        action = {

        }
    )
}