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


fun ApplicationState.pushRoute(routeName:String){
    this.router.navigate(routeName)
}

fun ApplicationState.replaceRoute(routeName:String){
    this.router.navigate(routeName){
        launchSingleTop = true
    }
}

fun ApplicationState.pushAndReplace(routeName:String){
    this.router.navigate(routeName){
        popUpTo(currentRoute)
        launchSingleTop = true
    }
}

fun ApplicationState.pushAndReplaceAll(routeName:String){
    this.router.navigate(routeName){
        popUpTo(currentRoute){
            inclusive = true
        }
        launchSingleTop = true
    }
}

suspend fun ApplicationState.showSnackbar(message: String): SnackbarResult = with(snackbarHostState) {
    if (snackBarType != "BASIC") {
        snackBarType = "BASIC"
    }
    showSnackbar(message)
}

suspend fun ApplicationState.showShortSnackbar(message: String): SnackbarResult = with(snackbarHostState) {
    if (snackBarType != "BASIC") {
        snackBarType = "BASIC"
    }
    showSnackbar(message, duration = SnackbarDuration.Short)
}

suspend fun ApplicationState.showLongSnackbar(message: String): SnackbarResult = with(snackbarHostState) {
    if (snackBarType != "BASIC") {
        snackBarType = "BASIC"
    }
    showSnackbar(message, duration = SnackbarDuration.Long)
}


suspend fun ApplicationState.showSnackbar(message: String, type: String): SnackbarResult =
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
    if (snackBarType != "BASIC") {
        snackBarType = "BASIC"
    }
    showSnackbar(
        message = message,
        actionLabel = actionLabel,
        withDismissAction = withDismissAction,
        duration = duration
    )
}