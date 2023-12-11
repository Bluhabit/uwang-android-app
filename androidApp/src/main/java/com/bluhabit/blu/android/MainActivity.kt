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
import androidx.compose.foundation.layout.Column
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bluhabit.blu.android.presentation.authentication.onboard.OnboardScreen
import com.bluhabit.blu.android.presentation.authentication.onboard.OnboardViewModel
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
                    startDestination = Routes.Onboard.routeName,
                ) {
                    composable("/onboard") {
                        val viewModel = hiltViewModel<OnboardViewModel>()
                        OnboardScreen(
                            stateFlow = viewModel.state,
                            effectFlow = viewModel.onEffect,
                            onAction = viewModel::onAction
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

