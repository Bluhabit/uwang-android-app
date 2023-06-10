/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui

import androidx.compose.runtime.Composable

@Composable
inline fun <State, Event> UIWrapper(
    invoker: UIWrapperListener<State, Event>,
    content: UIWrapperListener<State, Event>.() -> Unit
) {
    content(invoker)
}

@Composable
inline fun <State, Data, Event> UiWrapperData(
    invoker: UIWrapperListenerData<State, Data, Event>,
    content: UIWrapperListenerData<State, Data, Event>.() -> Unit
) {
    content(invoker)
}
