/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signup.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.presentation.authentication.signup.SignUpAction
import com.bluhabit.blu.android.presentation.authentication.signup.SignUpState
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.components.textfield.TextFieldPasswordPrimary
import com.bluhabit.core.ui.components.textfield.TextFieldState
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTheme
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun InputSetNewPasswordSignUpScreen(
    modifier: Modifier = Modifier,
    state: SignUpState = SignUpState(),
    onBackPressed: () -> Unit,
    action: (SignUpAction) -> Unit = {},
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
            .padding(horizontal = dimens.dp_16, vertical = dimens.dp_24),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Box(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "arrow back",
                    modifier = modifier
                        .size(dimens.dp_24)
                        .align(Alignment.CenterStart)
                        .clickable {
                            onBackPressed()
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "",
                    modifier = modifier
                        .size(dimens.dp_24)
                        .align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.padding(bottom = dimens.dp_24))
            Text(
                text = stringResource(id = R.string.reset_password_input_password_header),
                style = UwangTypography.BodyLarge.SemiBold,
                color = UwangColors.Text.Main
            )
            Spacer(modifier = Modifier.padding(bottom = 4.dp))
            Text(
                text = stringResource(id = R.string.reset_password_input_password_description_header),
                style = UwangTypography.BodySmall.Regular,
                color = UwangColors.Text.Secondary
            )
            Spacer(modifier = modifier.padding(bottom = dimens.dp_16))
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                TextFieldPasswordPrimary(
                    modifier = modifier.fillMaxWidth(),
                    label = stringResource(id = R.string.reset_password_input_password_label),
                    placeholder = stringResource(id = R.string.reset_password_input_password_placeholder),
                    value = state.passwordState,
                    state = state.passwordInputState,
                    showPassword = state.passwordVisibility,
                    onValueChange = {
                        action(SignUpAction.OnPasswordChange(it))
                    },
                    onChangeVisibility = {
                        action(SignUpAction.OnPasswordVisibilityChange(it))
                    },
                    onAction = {
                        it.clearFocus()
                    }
                )
                Spacer(modifier = modifier.height(10.dp))
                TextFieldPasswordPrimary(
                    modifier = modifier.fillMaxWidth(),
                    label = stringResource(id = R.string.reset_password_input_confirm_password_label),
                    placeholder = stringResource(id = R.string.reset_password_input_confirm_password_placeholder),
                    value = state.confirmPasswordState,
                    state = state.confirmPasswordInputState,
                    showPassword = state.passwordConfirmationVisibility,
                    onValueChange = {
                        action(SignUpAction.OnPasswordConfirmationChange(it))

                    },
                    onChangeVisibility = {
                        action(SignUpAction.OnPasswordConfirmationVisibilityChange(it))
                    },
                    onAction = {
                        it.clearFocus()
                    }
                )
            }
            Spacer(modifier = modifier.padding(bottom = dimens.dp_12))
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(dimens.dp_24),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ButtonPrimary(
                modifier = modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.reset_password_screen_next),
                enabled = state.passwordState.isNotEmpty()
                        && state.confirmPasswordState.isNotEmpty()
                        && state.passwordInputState !is TextFieldState.Error
                        && state.confirmPasswordInputState !is TextFieldState.Error
                        && state.otpAttempt < 3
            ) {
                action(SignUpAction.OnSetPassword)
            }
        }
    }
}

@Preview
@Composable
fun InputSetPasswordSignUpScreenPreview() {
    UwangTheme {
        InputSetNewPasswordSignUpScreen(
            state = SignUpState(),
            onBackPressed = {},
            action = {},
        )
    }
}