/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.dashboard

import androidx.lifecycle.viewModelScope
import com.bluhabit.blu.android.common.BaseViewModel
import com.bluhabit.blu.android.data.authentication.domain.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase
) : BaseViewModel<DashboardState, DashboardAction, DashboardEffect>(
    DashboardState()
) {
    override fun onAction(action: DashboardAction) {
        when (action) {
            DashboardAction.SignOut -> {
                viewModelScope.launch {
                    signOutUseCase()
                    sendEffect(DashboardEffect.Exit)
                }
            }
        }
    }
}