/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.components.DashboardBottomNavigation
import com.bluehabit.budgetku.android.rememberApplicationState
import com.bluehabit.budgetku.android.ui.BudgetKuTheme
import compose.icons.FeatherIcons
import compose.icons.feathericons.Plus

@Composable
fun BaseMainApp(
    appState: ApplicationState = rememberApplicationState(),
    topAppBar: @Composable (ApplicationState) -> Unit = {
        if (it.showTopAppBar) {
            it.topAppBar.invoke()
        }
    },
    snackBar: @Composable (ApplicationState) -> Unit = { state ->
        SnackbarHost(
            hostState = state.snackbarHostState,
            snackbar = {
                state.snackbar.invoke(it)
            }
        )

    },
    bottomBar: @Composable (ApplicationState) -> Unit = { state ->
        if (state.showBottomAppBar) {
            DashboardBottomNavigation(
                currentRoute = state.currentRoute,
                onRefresh = { state.event.refresh(it) },
                onClick = { state.event.navigate(it) }
            )
        }
    },
    bottomSheet: @Composable (ApplicationState) -> Unit = {
        it.bottomSheet.invoke()
    },
    content: @Composable (appState: ApplicationState) -> Unit = { }
) {

    BudgetKuTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.surface
        ) {
            ModalBottomSheetLayout(
                sheetContent = {
                    Column(Modifier.defaultMinSize(minHeight = 20.dp)) {
                        bottomSheet(appState)
                    }
                },
                sheetState = appState.bottomSheetState,
            ) {
                Scaffold(
                    topBar = {
                        topAppBar(appState)
                    },
                    bottomBar = {
                        bottomBar(appState)
                    },
                    snackbarHost = {
                        snackBar(appState)
                    },
                    isFloatingActionButtonDocked = true,
                    floatingActionButton = {
                        if (appState.showBottomAppBar) {
                            FloatingActionButton(
                                onClick = {
                                    appState.event.onFab()
                                },
                                backgroundColor = MaterialTheme.colors.primary,
                                elevation = FloatingActionButtonDefaults.elevation(
                                    focusedElevation = 0.dp,
                                    pressedElevation = 0.dp,
                                    hoveredElevation = 0.dp,
                                    defaultElevation = 0.dp
                                )
                            ) {
                                Icon(
                                    imageVector = FeatherIcons.Plus,
                                    contentDescription = "",
                                    tint = MaterialTheme.colors.onPrimary
                                )
                            }
                        }
                    },
                    floatingActionButtonPosition = FabPosition.Center
                ) {
                    Column(
                        modifier = Modifier.padding(it)
                    ) {
                        content(appState)
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun PreviewBaseMainApp() {
    BaseMainApp()
}