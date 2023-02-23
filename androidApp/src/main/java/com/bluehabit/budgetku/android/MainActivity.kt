package com.bluehabit.budgetku.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalConfiguration
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.EventListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var eventListener: EventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appState = rememberApplicationState()
            val localConfig = LocalConfiguration.current

            LaunchedEffect(key1 = appState.router, block = {
                appState.listenChanges(
                    ctx = this@MainActivity,
                    config = localConfig
                )
            })
            BaseMainApp(
                appState = appState,
                eventListener = eventListener
            ) { state, event ->
                AppNavigation(applicationState = state, eventListener = event)
            }
        }
    }
}
