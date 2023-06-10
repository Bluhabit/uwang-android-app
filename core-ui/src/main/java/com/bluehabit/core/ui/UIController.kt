/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui


import android.content.Context
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue.Hidden
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bluehabit.core.ui.extensions.runSuspend
import com.bluehabit.core.ui.listener.EventListener
import kotlinx.coroutines.CoroutineScope


class CreateSnackbarContent(
    private val content: @Composable (SnackbarData) -> Unit
) {
    @Composable
    fun invoke(snackbarData: SnackbarData) {
        content.invoke(snackbarData)
    }
}

class UIController internal constructor(
    val router: NavHostController,
    val bottomSheetState: ModalBottomSheetState,
    val scope: CoroutineScope,
    val event: EventListener,
    val context: Context
) {

    var currentRoute by mutableStateOf("")

    var snackbarHostState by mutableStateOf(
        SnackbarHostState()
    )

    internal var snackbar by mutableStateOf(CreateSnackbarContent {
        Snackbar(snackbarData = it)
    })

    fun showSnackbar(message: String) {
        runSuspend {
            snackbarHostState.showSnackbar(
                message,
                duration = SnackbarDuration.Short
            )
        }
    }

    fun showSnackbar(message: Int) {
        runSuspend {
            snackbarHostState.showSnackbar(
                context.getString(message),
                duration = SnackbarDuration.Short
            )
        }
    }

    fun showSnackbar(message: Int, vararg params: String) {
        runSuspend {
            snackbarHostState.showSnackbar(
                context.getString(message, *params),
                duration = SnackbarDuration.Short
            )
        }
    }

    fun showSnackbar(message: String, duration: SnackbarDuration) {
        runSuspend {
            snackbarHostState.showSnackbar(
                message,
                duration = duration
            )
        }
    }

    fun showSnackbar(message: String, actionLabel: String, onAction: () -> Unit = {}) {
        runSuspend {
            when (snackbarHostState.showSnackbar(
                message,
                actionLabel = actionLabel
            )) {
                SnackbarResult.Dismissed -> Unit
                SnackbarResult.ActionPerformed -> onAction()
            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberUIController(
    router: NavHostController = rememberNavController(),
    scope: CoroutineScope = rememberCoroutineScope(),
    event: EventListener = EventListener(),
    context: Context = LocalContext.current
): UIController {
    val state = rememberModalBottomSheetState(
        initialValue = Hidden,
        confirmValueChange = {
            event.changeBottomSheetState(it)
            true
        }
    )
    return remember {
        UIController(
            router,
            state,
            scope,
            event,
            context
        )
    }
}