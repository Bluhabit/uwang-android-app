/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BaseScreen(
    controller: UIController = rememberUIController(),
    backgroundColor: Color = MaterialTheme.colors.surface,
    topAppBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    bottomSheet: @Composable () -> Unit = { },
    content: @Composable (appState: UIController) -> Unit = { }
) {
    ModalBottomSheetLayout(
        sheetContent = {
            Column(
                Modifier
                    .defaultMinSize(
                        minHeight = 50.dp
                    )
                    .wrapContentHeight()
            ) {
                bottomSheet()
            }
        },
        sheetState = controller.bottomSheetState,
        sheetBackgroundColor = MaterialTheme.colors.surface,
        sheetShape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp
        )
    ) {
        Scaffold(
            topBar = topAppBar,
            bottomBar = bottomBar,
            snackbarHost = {
                SnackbarHost(
                    hostState = controller.snackbarHostState,
                    snackbar = { controller.snackbar.invoke(it) })
            },
            backgroundColor=backgroundColor,
            contentColor = MaterialTheme.colors.onSurface,
        ) {
            Column(
                modifier = Modifier.padding(it)
            ) {
                content(controller)
            }
        }
    }
}