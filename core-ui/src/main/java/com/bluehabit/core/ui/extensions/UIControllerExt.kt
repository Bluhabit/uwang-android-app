/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.extensions

import com.bluehabit.core.ui.UIController
import com.bluehabit.core.ui.listener.AppStateEventListener
import com.bluehabit.core.ui.listener.BottomSheetStateListener
import com.bluehabit.core.ui.listener.ScreenToAppEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


//region route
fun UIController.backPressedAndClose() {
    router.popBackStack(
        currentRoute,
        inclusive = true
    )
    exit()
}

fun UIController.navigateUp() {
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
fun UIController.navigate(routeName: String, vararg args:String) {
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
fun UIController.navigateSingleTop(routeName: String, vararg args:String) {
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
fun UIController.navigateSingleTop(routeName: String) {
    this.router.navigate(routeName) {
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
fun UIController.navigateAndReplace(routeName: String, vararg args:String) {
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
fun UIController.navigateAndReplaceAll(routeName: String, vararg args:String) {
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
fun UIController.navigateAndReplaceAll(routeName: String) {
    this.router.navigate(routeName) {
        popUpTo(currentRoute) {
            inclusive = true
        }
    }
}


//end region

//region coroutine
fun UIController.runSuspend(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = this.scope.launch(
    context, start, block
)

//end region


//region event
fun UIController.addOnEventListener(listener: AppStateEventListener) =
    event.addOnEventListener(listener)

fun UIController.addOnBottomSheetStateChangeListener(listener: BottomSheetStateListener) =
    event.addOnBottomSheetStateListener(listener)


fun UIController.sendEvent(eventName: ScreenToAppEvent) =
    event.sendEvent(eventName)

fun UIController.exit() =
    event.sendEvent(ScreenToAppEvent.EXIT_APP)
//end region

fun UIController.listenChanges() = this.router.addOnDestinationChangedListener { _, destination, _ ->
    currentRoute = destination.route.orEmpty()

}

fun UIController.showBottomSheet(){
    runSuspend {
        bottomSheetState.show()
    }
}

fun UIController.hideBottomSheet(){
    runSuspend {
        bottomSheetState.hide()
    }
}

//region string
fun UIController.getString(res:Int):String = context.getString(res)
fun UIController.getString(res:Int, vararg params:String):String = context.getString(res,*params)
//end region