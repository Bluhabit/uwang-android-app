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

@Composable
inline fun <State, reified ViewModel : BaseViewModel<State>> UIWrapper(
    appState: ApplicationState,
    parent: String = "",
    content: @Composable ViewModel.(uiState: State) -> Unit = {}
) {
    val vm = (if (parent.isNullOrEmpty()) {
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

@Composable
inline fun <State, Data, reified ViewModel : BaseDataViewModel<State, Data>> UIWrapper(
    appState: ApplicationState,
    parent: String = "",
    content: @Composable ViewModel.(uiState: State, data: Data) -> Unit = { _, _ ->
    }
) {
    val vm = (if (parent.isNullOrEmpty()) {
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
    val data by vm.dataState.collectAsState()
    content(vm, uiState, data)
}