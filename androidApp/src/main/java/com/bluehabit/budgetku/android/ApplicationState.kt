/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.*
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bluehabit.budgetku.android.base.EventListener
import com.bluehabit.budgetku.android.base.extensions.runSuspend
import com.bluehabit.budgetku.android.feature.splashScreen.Splash
import kotlinx.coroutines.CoroutineScope

class CreateContent(
    private val content: @Composable () -> Unit
) {
    @Composable
    fun invoke() {
        content.invoke()
    }
}

class CreateSnackbarContent(
    private val content: @Composable (SnackbarData) -> Unit
) {
    @Composable
    fun invoke(snackbarData: SnackbarData) {
        content.invoke(snackbarData)
    }
}

class ApplicationState internal constructor(
    val router: NavHostController,
    val scope: CoroutineScope,
    val event: EventListener
) {


    var currentRoute by mutableStateOf("")

    var snackbarHostState by mutableStateOf(
        SnackbarHostState()
    )

    internal var showBottomAppBar by mutableStateOf(false)
    internal var bottomAppBar by mutableStateOf(CreateContent {})

    internal var showTopAppBar by mutableStateOf(false)
    internal var topAppBar by mutableStateOf(CreateContent {})

    internal var snackbar by mutableStateOf(CreateSnackbarContent {
        Snackbar(snackbarData = it)
    })
    internal var bottomSheet by mutableStateOf(CreateContent {})

    @Composable
    fun setupBottomAppBar(
        content: @Composable () -> Unit = {}
    ) {
        LaunchedEffect(key1 = this, block = {
            bottomAppBar = CreateContent(content)
            if (!showBottomAppBar) {
                showBottomAppBar = true
            }
        })
    }

    @Composable
    fun setupTopAppBar(
        content: @Composable () -> Unit = {}
    ) {
        LaunchedEffect(key1 = this, block = {
            topAppBar = CreateContent(content)
            if (!showTopAppBar) {
                showTopAppBar = true
            }
        })

    }


    fun setupBottomSheet(content: @Composable () -> Unit = {}) {
        bottomSheet = CreateContent(content)
    }

    fun setupSnackbar(content: @Composable (SnackbarData) -> Unit = {}) {
        snackbar = CreateSnackbarContent(content)
    }

    fun setupDefaultSnackbar() {
        snackbar = CreateSnackbarContent {
            Snackbar(it)
        }
    }

    fun hideBottomAppBar() {
        showBottomAppBar = false
    }

    fun hideTopAppBar() {
        showTopAppBar = false
    }

    fun showSnackbar(message: String) {
        runSuspend {
            snackbarHostState.showSnackbar(message)
        }
    }

    fun showSnackbar(message: String, duration: SnackbarDuration) {
        runSuspend {
            snackbarHostState.showSnackbar(message, duration = duration)
        }
    }

    fun showSnackbar(message:String,actionLabel:String,onAction:()->Unit={}){
        runSuspend {
            when(snackbarHostState.showSnackbar(message,actionLabel = actionLabel,withDismissAction = true)){
                SnackbarResult.Dismissed -> Unit
                SnackbarResult.ActionPerformed -> onAction()
            }
        }
    }

    fun reset() {
        bottomAppBar = CreateContent { }
        currentRoute = Splash.routeName
    }
}

@Composable
fun rememberApplicationState(
    router: NavHostController = rememberNavController(),
    scope: CoroutineScope = rememberCoroutineScope(),
    event: EventListener = EventListener()
): ApplicationState {
    return remember {
        ApplicationState(
            router,
            scope,
            event
        )
    }
}