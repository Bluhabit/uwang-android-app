/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import com.bluehabit.budgetku.android.ApplicationState

@Composable
fun BaseSnackbar(
    state: ApplicationState
) {
    SnackbarHost(
        hostState = state.snackbarHostState,
        snackbar = {
            with(state) {
                when (snackBarType) {
                    "BASIC" -> {
                        Snackbar(
                            snackbarData = it,
                            contentColor = MaterialTheme.colorScheme.onSurface,
                            containerColor = MaterialTheme.colorScheme.surface,
                            actionColor = MaterialTheme.colorScheme.primary,
                            actionContentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    else -> Unit
                }
            }
        }
    )
}