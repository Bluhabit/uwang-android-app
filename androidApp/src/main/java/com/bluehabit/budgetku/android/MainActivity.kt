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
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.EventListener
import com.bluehabit.budgetku.android.base.extensions.listenChanges
import com.bluehabit.budgetku.android.base.listener.ToAppStateEventListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var eventListener: EventListener
    private lateinit var appState: ApplicationState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventListener = EventListener()
        setContent {
            appState = rememberApplicationState(
                event = eventListener
            )
            LaunchedEffect(key1 = appState.router, block = {
                appState.listenChanges()
            })
            BaseMainApp(appState = appState) {
                AppNavigation(applicationState = it)
            }
        }
        listenFromChild()
    }

    private fun listenFromChild() {
        eventListener.addOnEventListener(object : ToAppStateEventListener {
            override fun onEvent(eventName: String) {
                TODO("Not yet implemented ")
            }

            override fun exit() {
                finish()
            }

        })
    }
}

