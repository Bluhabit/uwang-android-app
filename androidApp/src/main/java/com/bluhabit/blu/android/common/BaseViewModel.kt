/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, Action, Effect>(
    private val initialState: State
) : ViewModel() {
    protected val _effect: Channel<Effect> = Channel(Channel.BUFFERED)
    val onEffect = _effect.consumeAsFlow()

    protected val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()
    abstract fun onAction(action: Action)

    protected fun updateState(s: State.() -> State) {
        _state.tryEmit(s(state.value))
    }

    protected fun sendEffect(effect: Effect) = viewModelScope.launch {
        _effect.send(effect)
    }
}