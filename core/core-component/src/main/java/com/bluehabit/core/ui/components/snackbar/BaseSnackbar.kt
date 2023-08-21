/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.snackbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.extensions.from

@Composable
fun BaseSnackbar(
    host: SnackbarHostState,
    content: @Composable (SnackbarData) -> Unit
) {
    val ctx = LocalContext.current
    SnackbarHost(
        hostState = host,
    ) {
        Column(
            modifier = Modifier.padding(
                start = 18.dp,
                end = 18.dp,
                bottom = 65.dp.from(context = ctx)
            )
        ) {
            content(it)
        }
    }
}