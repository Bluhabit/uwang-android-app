/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.dashboard

import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.eureka.data.authentication.domain.SignOutUseCase
import com.bluehabit.eureka.feature.dashboard.DashboardAction
import com.bluehabit.eureka.feature.dashboard.DashboardEffect
import com.bluehabit.eureka.feature.dashboard.DashboardState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase
) : MviViewModel<DashboardState, DashboardAction>(DashboardState()) {

    override fun onAction(action: DashboardAction) {
        when (action) {
            is DashboardAction.SetName -> Unit
            DashboardAction.SignOut ->
                async {
                    signOutUseCase()
                    commit { copy(effect = DashboardEffect.CloseApp) }
                }

        }
    }
}