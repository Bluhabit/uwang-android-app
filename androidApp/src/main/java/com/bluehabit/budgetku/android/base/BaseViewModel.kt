/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.base

import androidx.lifecycle.ViewModel
import com.bluehabit.budgetku.android.ApplicationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<State>(
    initialState: State
) : ViewModel() {
    protected val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState get() = _uiState.asStateFlow()

    protected lateinit var app: ApplicationState

    fun setAppState(appState: ApplicationState){
        app = appState
    }

}

abstract class BaseDataViewModel<FormState,Data>(
    initialState: FormState,
    initialData:Data
) : ViewModel() {
    protected val _uiState: MutableStateFlow<FormState> = MutableStateFlow(initialState)
    val uiState get() = _uiState.asStateFlow()

    protected val _dataState:MutableStateFlow<Data> = MutableStateFlow(initialData)
    val dataState get() = _dataState.asStateFlow()
    protected lateinit var app: ApplicationState

    fun setAppState(appState: ApplicationState){
        app = appState
    }

}