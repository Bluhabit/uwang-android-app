package com.bluehabit.budgetku.android.components

import androidx.compose.runtime.Composable
import com.bluehabit.budgetku.android.ApplicationState

@Composable
fun BaseBottomSheet(
    state: ApplicationState
) {
    with(state) {
        when (bottomSheetType) {
            else -> Unit
        }
    }
}