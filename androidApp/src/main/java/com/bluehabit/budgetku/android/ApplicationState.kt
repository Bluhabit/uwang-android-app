/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bluehabit.budgetku.android.base.EventListener
import com.bluehabit.budgetku.android.feature.splashScreen.Splash
import kotlinx.coroutines.CoroutineScope

class ApplicationState internal constructor(
    val router: NavHostController,
    val scope:CoroutineScope,
    val event:EventListener
) {
    var topAppBarType by mutableStateOf("")
    var bottomAppBarType by mutableStateOf("")
    var snackBarType by mutableStateOf("")
    var bottomSheetType by mutableStateOf("")

    var currentRoute by mutableStateOf("")

    var snackbarHostState by mutableStateOf(
        SnackbarHostState()
    )

    fun changeBottomBar(type: String) {
        if (bottomAppBarType != type) {
            bottomAppBarType = type
        }
    }


    fun changeTopAppBar(type: String) {
        if (topAppBarType != type) {
            topAppBarType = type
        }
    }

    fun changeSnackbar(type: String) {
        if (snackBarType != type) {
            snackBarType = type
        }
    }

    fun changeBottomSheet(type: String) {
        if (bottomSheetType != type) {
            bottomSheetType = type
        }
    }

    fun clear(){
        bottomSheetType = ""
        bottomAppBarType = ""
        snackBarType = ""
        bottomSheetType = ""
        currentRoute = Splash.routeName
    }
}

@Composable
fun rememberApplicationState(
    router: NavHostController = rememberNavController(),
    scope: CoroutineScope = rememberCoroutineScope(),
    event: EventListener= EventListener()
): ApplicationState {
    return remember {
        ApplicationState(
            router,
            scope,
            event
        )
    }
}