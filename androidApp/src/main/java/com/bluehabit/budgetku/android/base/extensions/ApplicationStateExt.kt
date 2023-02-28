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
import com.bluehabit.budgetku.android.base.listener.AppBarListener
import com.bluehabit.budgetku.android.base.listener.AppBarListenerImpl
import com.bluehabit.budgetku.android.base.listener.AppStateEventListener
import com.bluehabit.budgetku.android.base.listener.BottomAppBarType
import com.bluehabit.budgetku.android.base.listener.BottomSheetListener
import com.bluehabit.budgetku.android.base.listener.BottomSheetType
import com.bluehabit.budgetku.android.base.listener.SnackbarListener
import com.bluehabit.budgetku.android.base.listener.SnackbarType
import com.bluehabit.budgetku.android.base.listener.TopAppBarType
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


fun ApplicationState.addOnAppBarListener(impl: AppBarListenerImpl) =
    event.addOnAppBarListener(
        impl
    )

fun ApplicationState.addOnAppBarListener(
    onNavButtonClicked: () -> Unit = {},
    onNavItemClicked: (id: String, params: Array<out String>) -> Unit = { _, _ -> },
    onFabClicked: (id: String, params: Array<out String>) -> Unit = { _, _ -> },
    onActionClicked: (id: String, params: Array<out String>) -> Unit = { _, _ -> }
) =
    event.addOnAppBarListener(
        object : AppBarListener {
            override fun onNavigationButtonClicked() {
                onNavButtonClicked()
            }

            override fun onNavigationItemClicked(id: String, vararg params: String) {
                onNavItemClicked(id, params)
            }

            override fun onFabClicked(id: String, vararg params: String) {
                onFabClicked(id, params)
            }

            override fun onActionItemClicked(id: String, vararg params: String) {
                onActionClicked(id, params)
            }

        }
    )


fun ApplicationState.actionItemClicked(id: String, vararg params: String) =
    event.actionItemClicked(id, *params)


fun ApplicationState.navigationButtonClicked() =
    event.navigationButtonClicked()


fun ApplicationState.navigationItemClick(id: String, vararg params: String) =
    event.navigationItemClick(
        id,
        *params
    )

fun ApplicationState.onFabClick(id: String, vararg params: String) =
    event.fabClicked(
        id,
        *params
    )


fun ApplicationState.addSnackbarBarListener(
    onContentClicked:(id:String,params:Array<out String>)->Unit={_,_->},
    onActionClicked:(id:String)->Unit={}
) =
    event.addSnackbarBarListener(
        object :SnackbarListener{
            override fun onContent(id: String, vararg params: String) {
                onContentClicked.invoke(id,params)
            }

            override fun onAction(id: String) {
                onActionClicked.invoke(id)
            }

        }
    )


fun ApplicationState.onSnackbarContentClick(id: String, params: Array<out String>) =
    event.snackbarContent(id, params)


fun ApplicationState.onSnackbarActionClick(id: String) =
    event.snackbarAction(id)


fun ApplicationState.addOnBottomSheetListener(
    onContentClick: (id: String, params: Array<out String>) -> Unit = { _, _ -> },
    onClose: () -> Unit = {},
    onActionClick: (id: String, params: Array<out String>) -> Unit = { _, _ -> }
) =
    event.addOnBottomSheetListener(
        object :BottomSheetListener{
            override fun onContentClick(id: String, vararg params: String) {
                onContentClick.invoke(id,params)
            }

            override fun onClose() {
                onClose.invoke()
            }

            override fun onAction(id: String, vararg params: String) {
                onActionClick.invoke(id,params)
            }

        }
    )


fun ApplicationState.bottomSheetAction(id: String, params: Array<out String>) =
    event.bottomSheetAction(id, params)


fun ApplicationState.bottomSheetContent(id: String, params: Array<out String>) =
    event.bottomSheetContent(id, params)


fun ApplicationState.bottomSheetClose() =
    event.bottomSheetClose()

//end region

//region type
fun ApplicationState.changeBottomBar(type: BottomAppBarType) {
    if (bottomAppBarType != type) {
        bottomAppBarType = type
    }
}


fun ApplicationState.changeTopAppBar(type: TopAppBarType) {
    if (topAppBarType != type) {
        topAppBarType = type
    }
}

fun ApplicationState.changeSnackbar(type: SnackbarType) {
    if (snackBarType != type) {
        snackBarType = type
    }
}

fun ApplicationState.changeBottomSheet(type: BottomSheetType) {
    if (bottomSheetType != type) {
        bottomSheetType = type
    }
}
//end region