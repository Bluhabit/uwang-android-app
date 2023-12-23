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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bluhabit.blu.android.presentation.authentication.onboard.OnboardScreen
import com.bluhabit.blu.android.presentation.authentication.onboard.OnboardViewModel
import com.bluhabit.blu.android.presentation.authentication.termAndCondition.TermAndConditionScreen
import com.bluhabit.blu.android.presentation.authentication.termAndCondition.TermAndConditionViewModel
import com.bluhabit.blu.android.presentation.authentication.signin.SignInScreen
import com.bluhabit.blu.android.presentation.authentication.signin.SignInViewModel
import com.bluhabit.core.ui.routes.Routes
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
        setContent {
            val navHostController = rememberNavController()
            UwangTheme(
                darkTheme = false
            ) {
                NavHost(
                    navController = navHostController,
                    startDestination = "sign_in_screen",
                ) {
                    composable("onboard") {
                        val viewModel = hiltViewModel<OnboardViewModel>()
                        OnboardScreen(
                            stateFlow = viewModel.state,
                            effectFlow = viewModel.onEffect,
                            onAction = viewModel::onAction
                        )
                    }
                    composable("term_and_condition") {
                        val viewModel = hiltViewModel<TermAndConditionViewModel>()
                        TermAndConditionScreen(
                            stateFlow = viewModel.state,
                            effectFlow = viewModel.onEffect,
                            onAction = viewModel::onAction
                          )
                    }
                    composable("sign_in_screen") {
                        val viewModel = hiltViewModel<SignInViewModel>()
                        SignInScreen(
                            stateFlow = viewModel.state,
                            effectFlow = viewModel.onEffect,
                            onAction = viewModel::onAction,
                        )
                    }
                }
            }
        }
    }

    fun addEventListener(listener: SseListener) {
        sseListener = listener
    }
}

