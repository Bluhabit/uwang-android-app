/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.signUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.Argument
import app.trian.mvi.NavType
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.extensions.hideKeyboard
import app.trian.mvi.ui.internal.contract.UIContract
import app.trian.mvi.ui.internal.rememberUIController
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.DialogConfirmation
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.eureka.data.authentication.AuthConstant.AUTH_SCREEN_COMPLETE_PROFILE
import com.bluehabit.eureka.data.authentication.AuthConstant.AUTH_SCREEN_OTP
import com.bluehabit.eureka.feature.authentication.signUp.screen.ScreenCompleteProfile
import com.bluehabit.eureka.feature.authentication.signUp.screen.ScreenOtp

@Navigation(
    route = Routes.SignUp.routeName,
    viewModel = SignUpViewModel::class
)
@Argument(
    name = Routes.SignUp.currentScreenArg,
    navType = NavType.Integer
)
@Composable
fun SignUpScreen(
    uiContract: UIContract<SignUpState, SignUpAction>
) = UIWrapper(uiContract = uiContract) {
    val context = LocalContext.current
    UseEffect<SignUpEffect>(
        commit = { copy(effect = SignUpEffect.Nothing) },
        onEffect = {
            when (this) {
                SignUpEffect.NavigateToHome -> navigator.navigateAndReplace(Routes.Home.routeName)
                SignUpEffect.Nothing -> Unit
            }
        }
    )

    DialogConfirmation(
        show = state.showDialogConfirmation,
        title = "Berhasil Daftar",
        message = "Tinggal 1 langkah lagi untuk melengkapi profile Anda.",
        icon = {
            Image(
                painter = painterResource(id = R.drawable.success_icon_purple),
                contentDescription = "",
                modifier = Modifier
                    .padding(1.dp)
                    .width(103.dp)
                    .height(103.dp)
            )
        },
        action = {
            Button(
                onClick = {
                    commit {
                        copy(
                            showDialogConfirmation = false,
                            currentScreen = AUTH_SCREEN_COMPLETE_PROFILE
                        )
                    }
                },
                modifier = Modifier
                    .width(276.dp)
                    .height(44.dp)
            ) {
                Text(text = "Lanjut")
            }
        }
    )
    when (state.currentScreen) {
        AUTH_SCREEN_OTP -> ScreenOtp(
            state = state,
            onChange = {
                commit { copy(otp = it) }
                if (it.length == 4) {
                    context.hideKeyboard()
                    dispatch(SignUpAction.SubmitOtp)
                }
            },
            onSubmit = {
                context.hideKeyboard()
                dispatch(SignUpAction.SubmitOtp)
            }
        )

        AUTH_SCREEN_COMPLETE_PROFILE -> ScreenCompleteProfile(
            state = state,
            onChangeFullName = {
                commit { copy(fullName = it) }
            },
            onChangePassword = {
                commit { copy(password = it) }
            },
            onChangeConfirmPassword = {
                commit { copy(confirmPassword = it) }
            },
            onSubmit = {
                context.hideKeyboard()
                dispatch(SignUpAction.SubmitCompleteProfile)
            }
        )

        else -> Unit
    }
}

@Preview
@Composable
fun PreviewSignUpScreen() {
    GaweanTheme {
        SignUpScreen(
            uiContract = UIContract(
                rememberUIController(),
                SignUpState()
            )
        )
    }
}