/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.utils.Response

@Composable
inline fun <State,Event, reified ViewModel : BaseViewModel<State,Event>> UIWrapper(
    appState: ApplicationState,
    parent: String = "",
    content: @Composable ViewModel.(uiState: State) -> Unit = {}
) {
    val vm = (if (parent.isEmpty()) {
        hiltViewModel()
    } else {
        val parentEntry = remember(key1 = appState.router.currentBackStackEntry) {
            appState.router.getBackStackEntry(parent)
        }
        hiltViewModel<ViewModel>(parentEntry)
    }).also {
        it.setAppState(appState)
    }
    val uiState by vm.uiState.collectAsState()
    content(vm, uiState)
}
