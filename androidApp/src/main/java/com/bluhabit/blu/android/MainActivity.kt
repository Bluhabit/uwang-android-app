/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.presentation.authentication.completeProfile.CompleteProfileScreen
import com.bluhabit.blu.android.presentation.authentication.completeProfile.CompleteProfileViewModel
import com.bluhabit.blu.android.presentation.authentication.forgotPassword.ForgotPasswordScreen
import com.bluhabit.blu.android.presentation.authentication.forgotPassword.ForgotPasswordViewModel
import com.bluhabit.blu.android.presentation.authentication.onboard.OnboardScreen
import com.bluhabit.blu.android.presentation.authentication.onboard.OnboardViewModel
import com.bluhabit.blu.android.presentation.authentication.signin.SignInScreen
import com.bluhabit.blu.android.presentation.authentication.signin.SignInViewModel
import com.bluhabit.blu.android.presentation.authentication.signup.SignUpScreen
import com.bluhabit.blu.android.presentation.authentication.signup.SignUpViewModel
import com.bluhabit.blu.android.presentation.authentication.termAndCondition.TermAndConditionScreen
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.theme.UwangTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var sseListener: SseListener? = null

    interface SseListener {
        fun onEvent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()
            UwangTheme(
                darkTheme = false
            ) {
                NavHost(
                    navController = navHostController,
                    startDestination = "onboard",
                ) {
                    composable("onboard") {
                        val viewModel = hiltViewModel<OnboardViewModel>()
                        OnboardScreen(
                            navHostController = navHostController,
                            stateFlow = viewModel.state,
                            effectFlow = viewModel.onEffect,
                            onAction = viewModel::onAction
                        )
                    }
                    composable("term_and_condition") {
                        TermAndConditionScreen(
                            onBackPressed = {
                                navHostController.navigateUp()
                            }
                        )
                    }
                    composable("sign_in") {
                        val viewModel = hiltViewModel<SignInViewModel>()
                        SignInScreen(
                            navHostController = navHostController,
                            stateFlow = viewModel.state,
                            effectFlow = viewModel.onEffect,
                            onAction = viewModel::onAction,
                        )
                    }
                    composable("sign_up") {
                        val viewModel = hiltViewModel<SignUpViewModel>()
                        SignUpScreen(
                            navHostController = navHostController,
                            stateFlow = viewModel.state,
                            effectFlow = viewModel.onEffect,
                            onAction = viewModel::onAction,
                        )
                    }
                    composable("forgot_password") {
                        val viewModel = hiltViewModel<ForgotPasswordViewModel>()
                        ForgotPasswordScreen(
                            navHostController = navHostController,
                            stateFlow = viewModel.state,
                            effectFlow = viewModel.onEffect,
                            onAction = viewModel::onAction
                        )
                    }
                    composable("complete_profile") {
                        val viewModel = hiltViewModel<CompleteProfileViewModel>()
                        CompleteProfileScreen(
                            navHostController = navHostController,
                            stateFlow = viewModel.state,
                            effectFlow = viewModel.onEffect,
                            onAction = viewModel::onAction
                        )
                    }
                    composable("home") {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .safeDrawingPadding(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.app_logo),
                                contentDescription = ""
                            )
                            Text(text = "Ini adalah halaman utama.")
                            ButtonPrimary(
                                text = "Keluar",
                                onClick = {

                                }
                            )

                        }
                    }
                }
            }
        }
    }

    fun addEventListener(listener: SseListener) {
        sseListener = listener
    }
}

