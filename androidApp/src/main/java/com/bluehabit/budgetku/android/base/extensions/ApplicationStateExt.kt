/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.base.extensions

import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.listener.BottomNavigationListener
import com.bluehabit.budgetku.android.base.listener.ToAppStateEventListener
import com.bluehabit.budgetku.android.base.listener.BottomSheetStateListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


//region route
fun ApplicationState.backPressedAndClose() {
    router.popBackStack(
        currentRoute,
        inclusive = true
    )
    exit()
}

fun ApplicationState.navigateUp() {
    router.navigateUp()
}

/**
 * Navigation into [routeName] as destination and keep the previous route
 *
 * @param routeName is a destination
 * @param args is arguments/parameter also support multiple type
 *
 * @throws IllegalArgumentException from navHostController
 */
fun ApplicationState.navigate(routeName: String, vararg args: String) {
    var buildRoute = routeName
    if (args.isNotEmpty()) {
        args.forEach {
            buildRoute = buildString {
                append(routeName)
                append("/")
                append(it)
            }
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
fun ApplicationState.navigateSingleTop(routeName: String, vararg args: String) {
    var buildRoute = routeName
    if (args.isNotEmpty()) {
        args.forEach {
            buildRoute = buildString {
                append(routeName)
                append("/")
                append(it)
            }
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
fun ApplicationState.navigateAndReplace(routeName: String, vararg args: String) {
    var buildRoute = routeName
    if (args.isNotEmpty()) {
        args.forEach {
            buildRoute = buildString {
                append(routeName)
                append("/")
                append(it)
            }
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
fun ApplicationState.navigateAndReplaceAll(routeName: String, vararg args: String) {
    var buildRoute = routeName
    if (args.isNotEmpty()) {
        args.forEach {
            buildRoute = buildString {
                append(routeName)
                append("/")
                append(it)
            }
        }
    }
    this.router.navigate(buildRoute) {
        popUpTo(currentRoute) {
            inclusive = true
        }
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


//region event
fun ApplicationState.addOnBottomSheetStateChangeListener(listener: BottomSheetStateListener) =
    event.addOnBottomSheetStateListener(listener)

fun ApplicationState.bottomNavigationListener(listener: BottomNavigationListener) =
    event.bottomNavigationListener(listener)

fun ApplicationState.sendEvent(eventName: String) = event.sendEvent(eventName)

fun ApplicationState.exit() =
    event.sendEvent("EXIT")
//end region

fun ApplicationState.showBottomSheet() {
    runSuspend {
        bottomSheetState.show()
    }
}

fun ApplicationState.hideBottomSheet() {
    runSuspend {
        bottomSheetState.hide()
    }
}

fun ApplicationState.showBottomBar() {
    if (!showBottomAppBar) {
        showBottomAppBar = true
    }
}

fun ApplicationState.hideBottomBar() {
    if (showBottomAppBar) {
        showBottomAppBar = false
    }
}