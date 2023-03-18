/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.home

import com.bluehabit.budgetku.android.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : BaseViewModel<HomeState, HomeEvent>(HomeState()) {
    init {
        handleEvent()
    }
    override fun handleEvent() = onEvent {
        when (it) {
            is HomeEvent.SetName -> {
                updateState(
                    uiState.value.copy(
                        name = it.name
                    )
                )
            }
        }
    }
}