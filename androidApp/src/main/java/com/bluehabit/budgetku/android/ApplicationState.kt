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
import com.bluehabit.budgetku.android.base.listener.BottomAppBarType
import com.bluehabit.budgetku.android.base.listener.BottomSheetType
import com.bluehabit.budgetku.android.base.listener.SnackbarType
import com.bluehabit.budgetku.android.base.listener.TopAppBarType
import com.bluehabit.budgetku.android.feature.splashScreen.Splash
import kotlinx.coroutines.CoroutineScope

class ApplicationState internal constructor(
    val router: NavHostController,
    val scope:CoroutineScope,
    val event:EventListener
) {
    var topAppBarType by mutableStateOf(TopAppBarType.HIDE)
    var bottomAppBarType by mutableStateOf(BottomAppBarType.HIDE)
    var snackBarType by mutableStateOf(SnackbarType.BASIC)
    var bottomSheetType by mutableStateOf(BottomSheetType.BASIC)

    var currentRoute by mutableStateOf("")

    var snackbarHostState by mutableStateOf(
        SnackbarHostState()
    )

    fun changeBottomBar(type: BottomAppBarType) {
        if (bottomAppBarType != type) {
            bottomAppBarType = type
        }
    }


    fun changeTopAppBar(type: TopAppBarType) {
        if (topAppBarType != type) {
            topAppBarType = type
        }
    }

    fun changeSnackbar(type: SnackbarType) {
        if (snackBarType != type) {
            snackBarType = type
        }
    }

    fun changeBottomSheet(type: BottomSheetType) {
        if (bottomSheetType != type) {
            bottomSheetType = type
        }
    }

    fun clear(){
        bottomSheetType = BottomSheetType.BASIC
        bottomAppBarType = BottomAppBarType.HIDE
        snackBarType = SnackbarType.BASIC
        bottomSheetType = BottomSheetType.BASIC
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