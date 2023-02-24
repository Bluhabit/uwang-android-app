package com.bluehabit.budgetku.android.components

import androidx.compose.runtime.Composable
import com.bluehabit.budgetku.android.ApplicationState

@Composable
fun BaseTopAppBar(
    state: ApplicationState
) {
    with(state) {
        when (topAppBarType) {
            "BASIC" -> {

            }
            else -> Unit
        }
    }

}