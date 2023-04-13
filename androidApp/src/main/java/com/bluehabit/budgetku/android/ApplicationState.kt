/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android

import android.content.Context
import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bluehabit.budgetku.android.base.EventListener
import com.bluehabit.budgetku.android.base.extensions.runSuspend
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

enum class KeyboardState {
    Opened, Closed
}


class ApplicationState internal constructor(
    val router: NavHostController,
    val bottomSheetState: ModalBottomSheetState,
    val scope: CoroutineScope,
    val event: EventListener,
    val context: Context,
    val keyboardState: KeyboardState
) {


    var currentRoute by mutableStateOf("")

    var snackbarHostState by mutableStateOf(
        SnackbarHostState()
    )

    internal var showBottomAppBar by mutableStateOf(false)

    internal var showTopAppBar by mutableStateOf(false)
    internal var topAppBar by mutableStateOf(CreateContent {})

    internal var snackbar by mutableStateOf(CreateSnackbarContent {
        Snackbar(snackbarData = it)
    })
    internal var bottomSheet by mutableStateOf(CreateContent {})

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

    @Composable
    fun hideBottomAppBar() {
        LaunchedEffect(key1 = this, block = {
            if (showBottomAppBar) {
                showBottomAppBar = false
            }
        })
    }

    @Composable
    fun hideTopAppBar() {
        LaunchedEffect(key1 = this, block = {
            if (showTopAppBar) {
                showTopAppBar = false
            }
        })

    }

    @Composable
    fun showTopAppBar() {
        LaunchedEffect(key1 = this, block = {
            if (!showTopAppBar) {
                showTopAppBar = true
            }
        })
    }


    fun showSnackbar(message: String) {
        runSuspend {
            snackbarHostState.showSnackbar(message)
        }
    }

    fun showSnackbar(message: Int) {
        runSuspend {
            snackbarHostState.showSnackbar(context.getString(message))
        }
    }

    fun showSnackbar(message: Int, vararg params: String) {
        runSuspend {
            snackbarHostState.showSnackbar(context.getString(message, params))
        }
    }

    fun showSnackbar(message: String, duration: SnackbarDuration) {
        runSuspend {
            snackbarHostState.showSnackbar(message, duration = duration)
        }
    }

    fun showSnackbar(message: String, actionLabel: String, onAction: () -> Unit = {}) {
        runSuspend {
            when (snackbarHostState.showSnackbar(message, actionLabel = actionLabel, duration = SnackbarDuration.Indefinite)) {
                SnackbarResult.Dismissed -> Unit
                SnackbarResult.ActionPerformed -> onAction()
            }

        }
    }


}

@Composable
fun rememberApplicationState(
    router: NavHostController = rememberNavController(),
    scope: CoroutineScope = rememberCoroutineScope(),
    event: EventListener = EventListener(),
    context: Context = LocalContext.current
): ApplicationState {
    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = {
            event.changeBottomSheetState(it)
            true
        }
    )
    val keyboardState by rememberKeyboardState()
    return remember {
        ApplicationState(
            router,
            state,
            scope,
            event,
            context,
            keyboardState
        )
    }
}

@Composable
fun rememberKeyboardState(): State<KeyboardState> {
    val keyboardState = remember { mutableStateOf(KeyboardState.Closed) }
    val view = LocalView.current
    DisposableEffect(view) {
        val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            keyboardState.value = if (keypadHeight > screenHeight * 0.15) {
                KeyboardState.Opened
            } else {
                KeyboardState.Closed
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener)
        }
    }

    return keyboardState
}