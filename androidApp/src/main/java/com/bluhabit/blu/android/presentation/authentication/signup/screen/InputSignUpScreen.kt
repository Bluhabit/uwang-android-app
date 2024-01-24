/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signup.screen

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
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.presentation.authentication.signup.SignUpAction
import com.bluhabit.blu.android.presentation.authentication.signup.SignUpState
import com.bluhabit.core.ui.components.button.ButtonGoogle
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.components.textfield.TextFieldPrimary
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.CustomTypography
import com.bluhabit.core.ui.theme.UwangTheme

@Composable
fun InputSignUpScreen(
    state: SignUpState = SignUpState(),
    onTermAndCondition: () -> Unit,
    onBackPressed: () -> Unit,
    onSignIn: () -> Unit,
    onSignUpGoogle: () -> Unit,
    onAction: (SignUpAction) -> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState()),
    ) {
        IconButton(
            onClick = {
                onBackPressed()
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
                text = stringResource(id = R.string.sign_up_screen_title),
                style = CustomTypography.Body.XL.W600,
                color = UwangColors.Neutral.Grey13
            )
            Text(
                text = stringResource(id = R.string.sign_up_screen_instruction),
                style = CustomTypography.Body.Small.W400,
                color = UwangColors.Neutral.Grey9
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sign_up_screen_email_text_field_title),
                style = CustomTypography.Body.Small.W400,
                color = UwangColors.Neutral.Grey9
            )
            TextFieldPrimary(
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text(text = stringResource(id = R.string.sign_up_screen_email_text_field_label))
                },
                value = state.emailState,
                onValueChange = {
                    onAction(SignUpAction.OnEmailChange(value = it))
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
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sign_up_screen_password_text_field_title),
                style = CustomTypography.Body.Small.W400,
                color = UwangColors.Neutral.Grey9
            )
            TextFieldPrimary(
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text(stringResource(id = R.string.sign_up_screen_password_text_field_label))
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            onAction(SignUpAction.OnPasswordVisibilityChange(visibility = !state.passwordVisibility))
                        }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (!state.passwordVisibility) {
                                    R.drawable.ic_eye_closed
                                } else {
                                    R.drawable.ic_eye_open
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
                    onAction(SignUpAction.OnPasswordChange(value = it))
                },
                error = state.passwordError
            )
            Text(
                text = state.passwordErrorText,
                style = CustomTypography.Label.Small.W400,
                color = UwangColors.Error.Red300
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sign_up_screen_password_confirmation_text_field_title),
                style = CustomTypography.Body.Small.W400,
                color = UwangColors.Neutral.Grey9
            )
            TextFieldPrimary(
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text(stringResource(id = R.string.sign_up_screen_password_confirmation_text_field_label))
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            onAction(SignUpAction.OnPasswordConfirmationVisibilityChange(visibility = !state.passwordConfirmationVisibility))
                        }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (!state.passwordConfirmationVisibility) {
                                    R.drawable.ic_eye_closed
                                } else {
                                    R.drawable.ic_eye_open
                                }
                            ),
                            tint = UwangColors.Neutral.Grey7,
                            contentDescription = "",
                        )
                    }
                },
                visualTransformation = if (state.passwordConfirmationVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                value = state.passwordConfirmationState,
                onValueChange = {
                    onAction(SignUpAction.OnPasswordConfirmationChange(value = it))
                },
                error = state.passwordConfirmationError
            )
            Text(
                text = state.passwordConfirmationErrorText,
                style = CustomTypography.Label.Small.W400,
                color = UwangColors.Error.Red300
            )

        }
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        ButtonPrimary(
            text = stringResource(id = R.string.sign_up_screen_sign_up_button_text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            enabled = state.buttonEnabled
        ) {
            onAction(SignUpAction.SignUpBasic)
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
                text = stringResource(id = R.string.sign_up_screen_or),
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
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = stringResource(id = R.string.sign_up_screen_sign_in_google_button_text),
            onClick = {
                onSignUpGoogle()
            }
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = stringResource(id = R.string.sign_up_screen_already_have_an_account),
                style = CustomTypography.Body.Small.W400,
                color = UwangColors.Neutral.Grey9
            )
            Text(
                text = stringResource(id = R.string.sign_up_screen_sign_in),
                style = CustomTypography.Body.Small.W400,
                color = UwangColors.Primary.Blue500,
                modifier = Modifier
                    .clickable {
                        onSignIn()
                    }
            )
        }
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = stringResource(id = R.string.sign_up_screen_term_and_condition_1),
                style = CustomTypography.Body.Small.W400,
                color = UwangColors.Neutral.Grey7
            )
            Text(
                text = stringResource(id = R.string.sign_up_screen_term_and_condition_2),
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
fun TermAndConditionScreenPreview() {
    UwangTheme {
        InputSignUpScreen(
            onAction = {},
            onBackPressed = {},
            onTermAndCondition = {},
            onSignIn = {},
            onSignUpGoogle = {}
        )
    }
}
