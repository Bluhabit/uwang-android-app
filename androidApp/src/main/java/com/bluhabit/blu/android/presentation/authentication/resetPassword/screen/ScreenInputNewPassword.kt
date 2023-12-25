/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.resetPassword.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.presentation.authentication.resetPassword.ForgotPasswordState
import com.bluhabit.blu.android.presentation.authentication.signin.SignInAction
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.components.textfield.TextFieldPrimary
import com.bluhabit.core.ui.theme.CustomColor
import com.bluhabit.core.ui.theme.CustomTypography
import com.bluhabit.core.ui.theme.UwangTheme

@Composable
fun InputNewPasswordScreen(
    inputNewPasswordState: ForgotPasswordState = ForgotPasswordState(),
    onAction: (SignInAction) -> Unit = {},
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
                painter = painterResource(id = com.bluehabit.core.ui.R.drawable.ic_arrow_back),
                contentDescription = "arrow back"
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = com.bluehabit.core.ui.R.string.input_password_screen_reset_password),
                style = CustomTypography.Body.XL.W600,
                fontSize = 20.sp,
                color = CustomColor.Neutral.Grey13
            )
            Text(
                text = stringResource(id = com.bluehabit.core.ui.R.string.input_password_screen_hint),
                style = CustomTypography.Body.Small.W400,
                fontSize = 14.sp,
                color = CustomColor.Neutral.Grey9
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Column {
                Text(
                    text = stringResource(id = com.bluehabit.core.ui.R.string.input_password_screen_password),
                    fontSize = 14.sp,
                    style = CustomTypography.Body.Small.W400,
                    color = CustomColor.Neutral.Grey9
                )
                TextFieldPrimary(
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = {
                        Text(text = stringResource(id = R.string.input_password_screen_email_text_field_label))
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                onAction(SignInAction.PasswordAction(visibility = !inputNewPasswordState.passwordVisibility))
                            }
                        ) {
                            Icon(
                                painter = painterResource(
                                    id = if (!inputNewPasswordState.passwordVisibility) {
                                        R.drawable.ic_eye_closed
                                    } else {
                                        R.drawable.ic_eye_open
                                    }
                                ),
                                tint = CustomColor.Neutral.Grey7,
                                contentDescription = null,
                            )
                        }
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    value = inputNewPasswordState.passwordState,
                    onValueChange = {
                        onAction(SignInAction.PasswordAction(value = it))
                    },
                    error = inputNewPasswordState.passwordError
                )
            }
            Spacer(modifier = Modifier.padding(6.dp))
            Column {
                Text(
                    text = stringResource(id = com.bluehabit.core.ui.R.string.input_password_screen_Password_konfirm),
                    fontSize = 14.sp,
                    style = CustomTypography.Body.Small.W400,
                    color = CustomColor.Neutral.Grey9
                )
                TextFieldPrimary(
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = {
                        Text(text = stringResource(id = R.string.input_password_screen_email_text_field_label))
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                onAction(SignInAction.PasswordAction(visibility = !inputNewPasswordState.passwordVisibility))
                            }
                        ) {
                            Icon(
                                painter = painterResource(
                                    id = if (!inputNewPasswordState.passwordVisibility) {
                                        R.drawable.ic_eye_closed
                                    } else {
                                        R.drawable.ic_eye_open
                                    }
                                ),
                                tint = CustomColor.Neutral.Grey7,
                                contentDescription = null,
                            )
                        }
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    value = inputNewPasswordState.passwordState,
                    onValueChange = {
                        onAction(SignInAction.PasswordAction(value = it))
                    },
                    error = inputNewPasswordState.passwordError
                )
            }
        }
        if (inputNewPasswordState.emailError) {
            Text(
                text = stringResource(id = com.bluehabit.core.ui.R.string.input_password_screen_email_text_field_label),
                style = CustomTypography.Label.Small.W400,
                color = CustomColor.Error.Red300
            )
        }
    }
    Column(verticalArrangement = Arrangement.Bottom)
    {
        Spacer(modifier = Modifier.padding(380.dp))
        ButtonPrimary(
            text = stringResource(id = com.bluehabit.core.ui.R.string.input_password_screen_next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 42.dp),
            enabled = inputNewPasswordState.nextButtonEnabled
        ) {
            // Next
        }
    }
}

@Preview
@Composable
fun InputNewPasswordScreenPreview() {
    UwangTheme {
        InputNewPasswordScreen()
    }
}