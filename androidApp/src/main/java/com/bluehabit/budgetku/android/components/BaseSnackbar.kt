package com.bluehabit.budgetku.android.components

import androidx.compose.runtime.Composable
import com.bluehabit.budgetku.android.ApplicationState

@Composable
fun BaseSnackbar(
    state: ApplicationState
) {
    with(state) {
        when (snackBarType) {
            else -> Unit
        }
    }
}