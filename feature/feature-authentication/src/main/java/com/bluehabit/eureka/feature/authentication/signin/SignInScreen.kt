/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.signin

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.extensions.from
import app.trian.mvi.ui.internal.contract.UIContract
import app.trian.mvi.ui.internal.rememberUIController
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.components.button.ButtonSocial
import com.bluehabit.core.ui.input.InputTextPrimary
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.theme.Gray700
import com.bluehabit.core.ui.theme.Gray900
import com.bluehabit.core.ui.theme.Primary25
import com.bluehabit.core.ui.theme.Primary600
import com.bluehabit.core.ui.theme.Primary700
import com.bluehabit.eureka.feature.authentication.R

@Navigation(
    route = Routes.SignIn.routeName,
    viewModel = SignInViewModel::class
)
@Composable
fun SignInScreen(
    uiContract: UIContract<SignInState, SignInAction>,
    isEmailValid: Boolean = true,
    isPasswordValid: Boolean = true,
) = UIWrapper(uiContract = uiContract) {
    val context = LocalContext.current
    val tabs = listOf("Masuk", "Daftar")
    var selectedTabIndex by remember { mutableStateOf(0) }

    UseEffect<SignInEffect>(
        commit = { copy(effect = SignInEffect.Nothing) },
        onEffect = {
            when (this) {
                SignInEffect.Nothing -> Unit
                is SignInEffect.ShowDialog -> {
                    Toast.makeText(context, this.message, Toast.LENGTH_LONG).show()

                }
            }
        }
    )
    LaunchedEffect(key1 = this, block = {
        dispatch(SignInAction.CheckSession)
    })

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Primary25)
            .padding(
                vertical = 32.dp,
                horizontal = 20.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp.from(context = context), alignment = Alignment.Top)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp.from(context = context), alignment = Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = com.bluehabit.core.ui.R.drawable.gawean_logo ),
                contentDescription = "Logo Gawean"
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Selamat datang di Gawean",
                    style = MaterialTheme.typography.h6,
                    fontWeight = W600,
                    lineHeight = 30.sp,
                    color = Gray900,
                )
                Text(
                    text = "Daftar atau masuk agar bisa atur tugas lebih mudah dan selalu produktif.",
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = W400,
                    color = Gray900,
                    textAlign = TextAlign.Center
                )
            }
            TabRow(
                selectedTabIndex = selectedTabIndex,
                backgroundColor = Color.White,
                contentColor = Primary600
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(text = title) }
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp.from(context = context), alignment = Alignment.Top)
        ) {
            var emailInput by remember {
                mutableStateOf("azki@gmail.com")
            }
            var passwordInput by remember {
                mutableStateOf("123")
            }
            InputTextPrimary(
                label = "Email",
                value = emailInput,
                onChange = { emailInput = it },
                eror = !isEmailValid,
                enable = true,
            )
            InputTextPrimary(
                label = "Password",
                value = passwordInput,
                onChange = { passwordInput = it },
                eror = !isPasswordValid,
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
                        text = stringResource(R.string.remember_me),
                        style = MaterialTheme.typography.subtitle2,
                        fontWeight = W500,
                        color = Gray700,
                    )
                }
                Text(
                    text = stringResource(R.string.forget_password),
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = W500,
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
                    text = "Masuk",
                    enabled = emailInput.isNotEmpty() && passwordInput.isNotEmpty()
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp.from(context = context), Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = com.bluehabit.core.ui.R.drawable.line_4),
                        contentDescription = "line"
                    )
                    Text(
                        text = stringResource(com.bluehabit.core.ui.R.string.text_or),
                        style = MaterialTheme.typography.caption,
                        lineHeight = 18.sp,
                        fontWeight = W400,
                        color = Primary600,
                    )
                    Image(
                        painter = painterResource(id = com.bluehabit.core.ui.R.drawable.line_4),
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
                                    id = com.bluehabit.core.ui.R.drawable.social_icon_google
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
                                    id = com.bluehabit.core.ui.R.drawable.social_icon_facebook
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
                                    fontWeight = W400
                                )
                            ) {
                                append("Dengan masuk ke akun berarti Anda telah menyetujui")
                            }
                            append(" ")
                            withStyle(
                                style = SpanStyle(
                                    color = Primary700,
                                    fontStyle = MaterialTheme.typography.body1.fontStyle,
                                    fontWeight = W400
                                )
                            ) {
                                append("Syarat & Ketentuan")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Gray900,
                                    fontStyle = MaterialTheme.typography.body1.fontStyle,
                                    fontWeight = W400
                                )
                            ) {
                                append(" dan ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Primary700,
                                    fontStyle = MaterialTheme.typography.body1.fontStyle,
                                    fontWeight = W400
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
}

@Composable
@Preview(widthDp = 384, heightDp = 854, backgroundColor = 0xFFFCFAFF)
fun PreviewSignInScreen() {
    SignInScreen(
        uiContract = UIContract(
            controller = rememberUIController(),
            state = SignInState()
        )
    )
}