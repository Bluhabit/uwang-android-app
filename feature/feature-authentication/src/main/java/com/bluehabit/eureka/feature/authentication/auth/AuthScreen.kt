/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.auth

import android.util.Patterns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.extensions.Empty
import app.trian.mvi.ui.extensions.from
import app.trian.mvi.ui.extensions.getScreenHeight
import app.trian.mvi.ui.extensions.hideKeyboard
import app.trian.mvi.ui.internal.contract.UIContract
import app.trian.mvi.ui.internal.rememberUIController
import com.bluehabit.core.ui.components.dialog.DialogLoading
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray900
import com.bluehabit.core.ui.theme.Primary25
import com.bluehabit.core.ui.theme.Primary600
import com.bluehabit.eureka.data.authentication.AuthConstant.AUTH_SCREEN_OTP
import com.bluehabit.eureka.data.authentication.AuthConstant.AUTH_SCREEN_SIGN_IN
import com.bluehabit.eureka.data.authentication.AuthConstant.AUTH_SCREEN_SIGN_UP
import com.bluehabit.eureka.data.common.Response
import com.bluehabit.eureka.data.contract.GoogleAuthContract
import com.bluehabit.eureka.feature.authentication.auth.screen.ScreenSignIn
import com.bluehabit.eureka.feature.authentication.auth.screen.ScreenSignUp

@Navigation(
    route = Routes.Auth.routeName,
    viewModel = AuthViewModel::class
)
@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    uiContract: UIContract<AuthState, AuthAction>,
) = UIWrapper(uiContract = uiContract) {
    val context = LocalContext.current
    val screenHeight = context.getScreenHeight()
    fun resetAlert() {
        commit { copy(isError = false, errorMessage = String.Empty) }
    }

    fun isEmailSignInValid(): Boolean {
        return state.emailSignIn.isNotEmpty() && Patterns
            .EMAIL_ADDRESS.matcher(state.emailSignIn).matches()
    }

    fun isEmailSignUpValid(): Boolean {
        return state.emailSignUp.isNotEmpty() && Patterns
            .EMAIL_ADDRESS.matcher(state.emailSignUp).matches()
    }

    fun isPasswordValid(): Boolean {
        return state.passwordSignIn.isNotEmpty()
    }

    val googleLauncher = rememberLauncherForActivityResult(
        contract = GoogleAuthContract(),
        onResult = {
            when (it) {
                is Response.Error -> {
                    toast.show(it.message)
                }

                Response.Loading -> Unit
                is Response.Result -> {
                    dispatch(AuthAction.SignInWithGoogle(it.data))
                }
            }

        })

    UseEffect<AuthEffect>(
        commit = { copy(effect = AuthEffect.Nothing) },
        onEffect = {
            when (this) {
                AuthEffect.Nothing -> Unit
                is AuthEffect.ShowDialog -> {
                    Toast.makeText(context, this.message, Toast.LENGTH_LONG).show()
                }

                AuthEffect.NavigateToHome -> navigator.navigateAndReplace(Routes.Dashboard.routeName)
                AuthEffect.NavigateToOtp -> navigator.navigateAndReplace(Routes.SignUp.routeName, AUTH_SCREEN_OTP.toString())
            }
        }
    )

    DialogLoading(
        show = state.isLoading
    )

    Scaffold {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .height(screenHeight)
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            top = 24.dp,
                            start = 20.dp,
                            end = 20.dp,
                        ),
                    verticalArrangement = Arrangement.spacedBy(24.dp.from(context = context), alignment = Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = com.bluehabit.core.ui.R.drawable.gawean_logo),
                        contentDescription = stringResource(id = com.bluehabit.core.ui.R.string.content_description_logo)
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = com.bluehabit.core.ui.R.string.text_title_screen_auth),
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.W600,
                            lineHeight = 30.sp,
                            color = Gray900,
                        )
                        Text(
                            text = stringResource(id = com.bluehabit.core.ui.R.string.text_description_screen_auth),
                            style = MaterialTheme.typography.subtitle2,
                            fontWeight = FontWeight.W400,
                            color = Gray900,
                            textAlign = TextAlign.Center
                        )
                    }
                    TabRow(
                        selectedTabIndex = state.selectedTab,
                        backgroundColor = Color.White,
                        contentColor = Primary600
                    ) {
                        state.tabs.forEach { tab ->
                            val (title, screenPosition) = tab
                            Tab(
                                selected = state.selectedTab == screenPosition,
                                onClick = {
                                    commit { copy(isError = false, selectedTab = screenPosition) }
                                },
                                text = { Text(text = title) },
                                modifier = modifier.testTag("tab_auth_$screenPosition")
                            )
                        }
                    }
                }
                Column(
                    modifier = modifier
                        .background(Primary25)
                ) {
                    when (state.selectedTab) {
                        AUTH_SCREEN_SIGN_IN -> ScreenSignIn(
                            state = state,
                            onEmailChanged = {
                                commit { copy(emailSignIn = it, emailSignInError = !isEmailSignInValid()) }
                            },
                            onPasswordChanged = {
                                commit { copy(passwordSignIn = it, passwordSignInError = !isPasswordValid()) }
                            },
                            onRememberChecked = {
                                commit { copy(isRememberChecked = it) }
                            },
                            onNavigateToResetPassword = {
                                navigator.navigateAndReplace(Routes.ResetPassword.routeName)
                            },
                            onSignInEmail = {
                                context.hideKeyboard()
                                dispatch(AuthAction.SignInWithEmail)
                            },

                            onSignInFacebook = {
                                toast.show("Coming soon")
                            },
                            onSignInGoogle = {
                                commit { copy(isLoading = true) }
                                googleLauncher.launch(1)
                            },
                            onResetAlert = {
                                resetAlert()
                            },
                            onShowPrivacyPolicy = {},
                            onShowTermCondition = {}
                        )

                        AUTH_SCREEN_SIGN_UP -> ScreenSignUp(
                            state = state,
                            onEmailChanged = {
                                commit { copy(emailSignUp = it, emailSignUpError = !isEmailSignUpValid()) }
                            },
                            onSignUpEmail = {
                                context.hideKeyboard()
                                dispatch(AuthAction.SignUpWithEmail)
                            },
                            onSignUpGoogle = {
                                commit { copy(isLoading = true) }
                                googleLauncher.launch(1)
                            },
                            onSignUpFacebook = {
                                toast.show("Coming soon")
                            },
                            onResetAlert = {
                                resetAlert()
                            },
                            onShowTermCondition = {},
                            onShowPrivacyPolicy = {}
                        )
                    }
                }
            }
        }
    }

}

@Preview(backgroundColor = 0xFFFCFAFF)
@Composable
fun PreviewSignInScreen() {
    var state by remember {
        mutableStateOf(AuthState())
    }
    GaweanTheme {
        AuthScreen(
            uiContract = UIContract(
                controller = rememberUIController(),
                state = state,
                mutation = {
                    state = it
                }
            )
        )
    }
}