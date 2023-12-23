/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signup

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.components.button.ButtonGoogle
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.components.textfield.TextFieldPrimary
import com.bluhabit.core.ui.theme.CustomColor
import com.bluhabit.core.ui.theme.CustomTypography
import com.bluhabit.core.ui.theme.UwangTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun SignUpScreen(
    signUpState: SignUpState = SignUpState(),
    stateFlow: Flow<SignUpState> = flowOf(),
    effectFlow: Flow<SignUpEffect> = flowOf(),
    onAction: (SignUpAction) -> Unit = {},
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
                // On Back Pressed
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
                text = "Daftarkan akun anda",
                style = CustomTypography.Body.XL.W600,
                color = CustomColor.Neutral.Grey13
            )
            Text(
                text = "Masukkan alamat email Anda dan buat password Anda untuk melakukan pendaftaran  ",
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
                text = "Email",
                style = CustomTypography.Body.Small.W400,
                color = CustomColor.Neutral.Grey9
            )
            TextFieldPrimary(
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text(text = "Contoh  : fadelptr09@gmail.com")
                },
                value = signUpState.emailState,
                onValueChange = {
                    onAction(SignUpAction.EmailAction(value = it))
                },
                error = signUpState.emailError
            )
            if (signUpState.emailError) {
                Text(
                    text = signUpState.emailErrorText,
                    style = CustomTypography.Label.Small.W400,
                    color = CustomColor.Error.Red300
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Password",
                style = CustomTypography.Body.Small.W400,
                color = CustomColor.Neutral.Grey9
            )
            TextFieldPrimary(
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text("Masukkan password Anda")
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            onAction(SignUpAction.PasswordAction(visibility = !signUpState.passwordVisibility))
                        }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (!signUpState.passwordVisibility) {
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
                value = signUpState.passwordState,
                onValueChange = {
                    onAction(SignUpAction.PasswordAction(value = it))
                },
                error = signUpState.passwordError
            )
            if (signUpState.passwordError) {
                Text(
                    text = signUpState.passwordErrorText,
                    style = CustomTypography.Label.Small.W400,
                    color = CustomColor.Error.Red300
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Konfirmasi Password",
                style = CustomTypography.Body.Small.W400,
                color = CustomColor.Neutral.Grey9
            )
            TextFieldPrimary(
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text("Konfirmasi password Anda")
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            onAction(SignUpAction.PasswordAction(visibility = !signUpState.passwordConfirmationVisibility))
                        }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (!signUpState.passwordConfirmationVisibility) {
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
                value = signUpState.passwordConfirmationState,
                onValueChange = {
                    onAction(SignUpAction.PasswordAction(value = it))
                },
                error = signUpState.passwordConfirmationError
            )
            if (signUpState.passwordConfirmationError) {
                Text(
                    text = signUpState.passwordConfirmationErrorText,
                    style = CustomTypography.Label.Small.W400,
                    color = CustomColor.Error.Red300
                )
            }
        }
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        ButtonPrimary(
            text = "Daftar",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            enabled = signUpState.buttonEnabled
        ) {
            // Sign in
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
                text = "Atau",
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
            text = "Daftar dengan Google",
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = "Sudah memiliki akun sebelumnya? ",
                style = CustomTypography.Body.Small.W400,
                color = CustomColor.Neutral.Grey9
            )
            Text(
                text = "Masuk",
                style = CustomTypography.Body.Small.W400,
                color = CustomColor.Primary.Blue500,
                modifier = Modifier
                    .clickable {
                        //On login clicked
                    }
            )
        }
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = "Dengan melakukan pendaftaran akun atau masuk ke dalam akun anda menyetujui",
                style = CustomTypography.Body.Small.W400,
                color = CustomColor.Neutral.Grey7
            )
            Text(
                text = "Syarat &amp; Ketentuan kebijakan aplikasi",
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

@Preview
@Composable
fun TermAndConditionScreenPreview() {
    UwangTheme {
        SignUpScreen()
    }
}
