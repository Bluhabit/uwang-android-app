/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.dashboard

import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.eureka.data.authentication.domain.SignOutUseCase
import com.bluehabit.eureka.data.common.Response
import com.bluehabit.eureka.data.common.executeAsFlow
import com.bluehabit.eureka.data.task.domain.GetListTaskUseCase
import com.bluehabit.eureka.feature.dashboard.DashboardAction
import com.bluehabit.eureka.feature.dashboard.DashboardEffect
import com.bluehabit.eureka.feature.dashboard.DashboardState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val getListTaskUseCase: GetListTaskUseCase
) : MviViewModel<DashboardState, DashboardAction>(DashboardState()) {

    private fun getAllTaskUseCase() = async {
        executeAsFlow { getListTaskUseCase(page = 0) }
            .collect {
                when (it) {
                    is Response.Error -> Unit
                    Response.Loading -> Unit
                    is Response.Result -> {
                        commit { copy(allTask = it.data.items) }
                    }
                }
            }
    }

    override fun onAction(action: DashboardAction) {
        when (action) {
            is DashboardAction.SetName -> Unit
            DashboardAction.SignOut ->
                async {
                    signOutUseCase()
                    commit { copy(effect = DashboardEffect.CloseApp) }
                }

            DashboardAction.GetListTask -> getAllTaskUseCase()
            is DashboardAction.GetListTaskByDate -> Unit
        }
    }
}