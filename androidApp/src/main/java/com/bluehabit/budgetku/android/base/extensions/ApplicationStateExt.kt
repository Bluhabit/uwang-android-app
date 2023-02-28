/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.base.extensions

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarResult
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.listener.AppBarListenerImpl
import com.bluehabit.budgetku.android.base.listener.AppStateEventListener
import com.bluehabit.budgetku.android.base.listener.BottomSheetListener
import com.bluehabit.budgetku.android.base.listener.SnackbarListener
import com.bluehabit.budgetku.android.base.listener.SnackbarType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


//region route
/**
 * Navigation into [routeName] as destination and keep the previous route
 *
 * @param routeName is a destination
 * @param args is arguments/parameter also support multiple type
 *
 * @throws IllegalArgumentException from navHostController
 */
fun ApplicationState.navigate(routeName: String, vararg args: Any) {
    var buildRoute = routeName
    if (args.isNotEmpty()) {
        args.forEach {
            buildRoute += "/$it"
        }
    }
    this.router.navigate(buildRoute)
}

/**
 * Navigation into [routeName] as destination and keep the previous route
 * if same route exist on back stack, will be replace with [routeName]
 *
 * @param routeName is a destination
 * @param args is arguments/parameter also support multiple type
 *
 * @throws IllegalArgumentException from navHostController
 */
fun ApplicationState.navigateSingleTop(routeName: String, vararg args: Any) {
    var buildRoute = routeName
    if (args.isNotEmpty()) {
        args.forEach {
            buildRoute += "/$it"
        }
    }
    this.router.navigate(buildRoute) {
        launchSingleTop = true
    }
}

/**
 * Navigation into [routeName] as destination, and pop all backstack before last route
 * if same route exist on back stack, will be replace with [routeName]
 *
 * @param routeName is a destination
 * @param args is arguments/parameter also support multiple type
 *
 * @throws IllegalArgumentException from navHostController
 */
fun ApplicationState.navigateAndReplace(routeName: String, vararg args: Any) {
    var buildRoute = routeName
    if (args.isNotEmpty()) {
        args.forEach {
            buildRoute += "/$it"
        }
    }
    this.router.navigate(buildRoute) {
        popUpTo(currentRoute)
        launchSingleTop = true
    }
}

/**
 * Navigation into [routeName] as destination, and pop all backstack from last route
 * if same route exist on back stack, will be replace with [routeName]
 *
 * @param routeName is a destination
 * @param args is arguments/parameter also support multiple type
 *
 *
 * @throws IllegalArgumentException from navHostController
 */
fun ApplicationState.navigateAndReplaceAll(routeName: String, vararg args: Any) {
    var buildRoute = routeName
    if (args.isNotEmpty()) {
        args.forEach {
            buildRoute += "/$it"
        }
    }
    this.router.navigate(buildRoute) {
        popUpTo(currentRoute) {
            inclusive = true
        }
        launchSingleTop = true
    }
}


//end region

//region coroutine
fun ApplicationState.runSuspend(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = this.scope.launch(
    context, start, block
)

//end region

//region snackbar
suspend fun ApplicationState.showSnackbar(message: String): SnackbarResult = with(snackbarHostState) {
    if (snackBarType != SnackbarType.BASIC) {
        snackBarType = SnackbarType.BASIC
    }
    showSnackbar(message)
}

suspend fun ApplicationState.showShortSnackbar(message: String): SnackbarResult = with(snackbarHostState) {
    if (snackBarType != SnackbarType.BASIC) {
        snackBarType = SnackbarType.BASIC
    }
    showSnackbar(message, duration = SnackbarDuration.Short)
}

suspend fun ApplicationState.showLongSnackbar(message: String): SnackbarResult = with(snackbarHostState) {
    if (snackBarType != SnackbarType.BASIC) {
        snackBarType = SnackbarType.BASIC
    }
    showSnackbar(message, duration = SnackbarDuration.Long)
}


suspend fun ApplicationState.showSnackbar(message: String, type: SnackbarType): SnackbarResult =
    with(snackbarHostState) {
        if (snackBarType != type) {
            snackBarType = type
        }
        return if (currentSnackbarData == null) {
            showSnackbar(message, duration = SnackbarDuration.Indefinite)
        } else SnackbarResult.Dismissed
    }

fun ApplicationState.hideSnackbar() = with(snackbarHostState) {
    currentSnackbarData?.dismiss()
}

suspend fun ApplicationState.showSnackbar(
    message: String,
    actionLabel: String? = null,
    withDismissAction: Boolean = false,
    duration: SnackbarDuration =
        if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite
) = with(snackbarHostState) {
    if (snackBarType != SnackbarType.BASIC) {
        snackBarType = SnackbarType.BASIC
    }
    showSnackbar(
        message = message,
        actionLabel = actionLabel,
        withDismissAction = withDismissAction,
        duration = duration
    )
}
//end region

//region event
fun ApplicationState.addOnEventListener(listener: AppStateEventListener) =
    event.addOnEventListener(listener)


fun ApplicationState.sendEvent(eventName: String) =
    event.sendEvent(eventName)


fun ApplicationState.addOnAppBarListener(listener: AppBarListenerImpl) =
    event.addOnAppBarListener(listener)


fun ApplicationState.actionItemClicked(id: String, vararg params:String) =
    event.actionItemClicked(id, *params)


fun ApplicationState.navigationButtonClicked() =
    event.navigationButtonClicked()


fun ApplicationState.navigationItemClick(id: String, vararg params: String) =
    event.navigationItemClick(
        id,
        *params
    )

fun ApplicationState.fabClicked(id: String, vararg params: String) =
    event.fabClicked(
        id,
        *params
    )


fun ApplicationState.addSnackbarBarListener(listener: SnackbarListener) =
    event.addSnackbarBarListener(listener)


fun ApplicationState.snackbarContent(id: String, params: Array<out String>) =
    event.snackbarContent(id, params)


fun ApplicationState.snackbarAction(id: String) =
    event.snackbarAction(id)


fun ApplicationState.addOnBottomSheetListener(listener: BottomSheetListener) =
    event.addOnBottomSheetListener(listener)


fun ApplicationState.bottomSheetAction(id: String, params: Array<out String>) =
    event.bottomSheetAction(id, params)


fun ApplicationState.bottomSheetContent(id: String, params: Array<out String>) =
    event.bottomSheetContent(id, params)


fun ApplicationState.bottomSheetClose() =
    event.bottomSheetClose()

//end region