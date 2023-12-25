/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signin.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.presentation.authentication.signin.SignInAction
import com.bluhabit.blu.android.presentation.authentication.signin.SignInState
import com.bluhabit.core.ui.components.button.ButtonGoogle
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.components.textfield.TextFieldPrimary
import com.bluhabit.core.ui.theme.CustomColor
import com.bluhabit.core.ui.theme.CustomTypography

@Composable
fun InputSignInScreen(
    state:SignInState=SignInState(),
    onSignInGoogle:()->Unit,
    onSignUp:()->Unit,
    onForgotPassword:()->Unit,
    action:(SignInAction)->Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState()),
    ) {
        IconButton(
            onClick = {
                // TODO
            }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "arrow back"
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sign_in_screen_welcome),
                style = CustomTypography.Body.XL.W600,
                color = CustomColor.Neutral.Grey13
            )
            Text(
                text = stringResource(id = R.string.sign_in_screen_description),
                style = CustomTypography.Body.Small.W400,
                color = CustomColor.Neutral.Grey9
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sign_in_screen_email_text_field_title),
                style = CustomTypography.Body.Small.W400,
                color = CustomColor.Neutral.Grey9
            )
            TextFieldPrimary(
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text(text = stringResource(id = R.string.sign_in_screen_email_text_field_label))
                },
                value = state.emailState,
                onValueChange = {
                    action(SignInAction.OnEmailChange(value = it))
                },
                error = state.emailError
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sign_in_screen_password_text_field_title),
                style = CustomTypography.Body.Small.W400,
                color = CustomColor.Neutral.Grey9
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
                            tint = CustomColor.Neutral.Grey7,
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
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            enabled = state.buttonEnabled
        ) {
            action(SignInAction.OnSignInBasic)
        }
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
        ButtonGoogle(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = stringResource(id = R.string.sign_in_screen_sign_in_google_button_text),
            onClick = {
                onSignInGoogle()
            }
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp),
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
                        onSignUp()
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