/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.signUp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
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
import androidx.compose.ui.unit.sp
import app.trian.mvi.ui.extensions.from
import app.trian.mvi.ui.extensions.getScreenHeight
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.components.input.InputPasswordPrimary
import com.bluehabit.core.ui.components.input.InputTextPrimary
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray900
import com.bluehabit.core.ui.theme.Primary25
import com.bluehabit.eureka.feature.authentication.signUp.SignUpState

@Composable
fun ScreenCompleteProfile(
    modifier: Modifier = Modifier,
    state: SignUpState = SignUpState(),
    onChangeFullName: (String) -> Unit = {},
    onChangePassword: (String) -> Unit = {},
    onChangeConfirmPassword: (String) -> Unit = {},
    onSubmit: () -> Unit = {}
) {
    val context = LocalContext.current
    val screenHeight = context.getScreenHeight()
    fun isValid(): Boolean {
        return state.fullName.isNotEmpty() && state.password.isNotEmpty()
                && state.confirmPassword.isNotEmpty()
    }
    Scaffold(
        bottomBar = {
            Row(
                modifier = modifier
                    .background(MaterialTheme.colors.surface)
                    .padding(
                        horizontal = 18.dp,
                        vertical = 10.dp
                    )
            ) {
                ButtonPrimary(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 10.dp
                        ),
                    text = stringResource(id = R.string.text_btn_complete_profile),
                    enabled = isValid() && (!state.fullNameError && !state.passwordError && !state.confirmPasswordError),
                    onClick = onSubmit
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(it)
                .height(screenHeight)
                .background(Primary25)
                .padding(
                    horizontal = 18.dp
                ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 24.dp,
                        horizontal = 20.dp,
                    ),
                verticalArrangement = Arrangement.spacedBy(24.dp.from(context = context), alignment = Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.gawean_logo),
                    contentDescription = stringResource(id = R.string.content_description_logo)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.text_title_complete_profile),
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.W600,
                        lineHeight = 30.sp,
                        color = Gray900,
                    )
                    Text(
                        text = stringResource(id = R.string.text_subtitle_complete_profile),
                        style = MaterialTheme.typography.subtitle2,
                        fontWeight = FontWeight.W400,
                        color = Gray900,
                        textAlign = TextAlign.Center
                    )
                }
            }
            InputTextPrimary(
                label = stringResource(id = R.string.text_label_input_full_name_complete_profile),
                placeholder = stringResource(id = R.string.text_placeholder_input_full_name_complete_profile),
                value = state.fullName,
                onChange = onChangeFullName,
                enabled = true,
                error = state.fullNameError,
                errorMessage = state.fullNameErrorMessage
            )
            InputPasswordPrimary(
                label = stringResource(id = R.string.text_label_password_complete_profile),
                placeholder = stringResource(id = R.string.text_placeholder_input_password_complete_profile),
                value = state.password,
                onChange = onChangePassword,
                error = state.passwordError,
                errorMessage = state.passwordErrorMessage
            )
            InputPasswordPrimary(
                label = stringResource(id = R.string.text_label_input_confirm_password_complete_profile),
                placeholder = stringResource(id = R.string.text_placeholder_input_confirm_password_complete_profile),
                value = state.confirmPassword,
                onChange = onChangeConfirmPassword,
                error = state.confirmPasswordError,
                errorMessage = state.confirmPasswordErrorMessage
            )
        }
    }
}

@Preview
@Composable
fun PreviewScreenCompleteProfile() {
    GaweanTheme {
        ScreenCompleteProfile()
    }
}