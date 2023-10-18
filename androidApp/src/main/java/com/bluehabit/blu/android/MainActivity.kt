/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.blu.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import app.trian.mvi.authenticationComponent
import app.trian.mvi.dashboardComponent
import app.trian.mvi.ui.internal.UIController
import app.trian.mvi.ui.internal.listener.BaseEventListener
import app.trian.mvi.ui.internal.listener.EventListener
import app.trian.mvi.ui.internal.rememberUIController
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.theme.GaweanTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var controller: UIController
    private lateinit var eventListener: BaseEventListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            eventListener = EventListener()
            controller = rememberUIController(
                event = eventListener
            )
            GaweanTheme(
                darkTheme = false
            ) {
                NavHost(
                    navController = controller.navigator.navHost,
                    startDestination = Routes.Onboard.routeName,
                ) {
                    authenticationComponent(
                        uiController = controller,
                        event = eventListener
                    )
                    dashboardComponent(
                        uiController = controller,
                        event = eventListener
                    )
                }
            }
        }
    }
}

