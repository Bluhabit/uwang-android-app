/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.UIController
import com.bluehabit.core.ui.extensions.listenChanges
import com.bluehabit.core.ui.listener.EventListener
import com.bluehabit.core.ui.listener.ScreenToAppEvent
import com.bluehabit.core.ui.rememberUIController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var eventListener: EventListener
    private lateinit var appState: UIController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventListener = EventListener()
        setContent {
            appState = rememberUIController(
                event = eventListener
            )
            LaunchedEffect(key1 = appState.router, block = {
                appState.listenChanges()
            })
            BaseMainApp(controller = appState) {
                AppNavigation(uiController = it)
            }
        }
        listenFromChild()
    }

    private fun listenFromChild() {
        eventListener.addOnEventListener {
            when(it){
                ScreenToAppEvent.EXIT_APP -> TODO()
            }
        }
    }
}

