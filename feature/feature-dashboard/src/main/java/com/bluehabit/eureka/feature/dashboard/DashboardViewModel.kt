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
import com.bluehabit.eureka.data.task.domain.GetAllListTaskUseCase
import com.bluehabit.eureka.data.task.domain.GetFinishListTaskUseCase
import com.bluehabit.eureka.data.task.domain.GetThisWeekListTaskUseCase
import com.bluehabit.eureka.data.task.domain.GetTodayListTaskUseCase
import com.bluehabit.eureka.data.task.domain.GetTomorrowListTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val getAllListTaskUseCase: GetAllListTaskUseCase,
    private val getTodayListTaskUseCase: GetTodayListTaskUseCase,
    private val getTomorrowListTaskUseCase: GetTomorrowListTaskUseCase,
    private val getThisWeekListTaskUseCase: GetThisWeekListTaskUseCase,
    private val getFinishListTaskUseCase: GetFinishListTaskUseCase
) : MviViewModel<DashboardState, DashboardAction>(DashboardState()) {

    private fun getAllTaskUseCase() = async {
        executeAsFlow { getAllListTaskUseCase(page = 0) }
            .collect {
                when (it) {
                    is Response.Error -> Unit
                    Response.Loading -> Unit
                    is Response.Result -> {
                        commit { copy(listAllTask = it.data) }
                    }
                }
            }
    }

    private fun getListTaskToday() = async {
        executeAsFlow { getTodayListTaskUseCase() }.collect {
            when (it) {
                is Response.Error -> Unit
                Response.Loading -> Unit
                is Response.Result -> commit { copy(listTask = it.data.items) }
            }
        }
    }

    private fun getTomorrowListTask() = async {
        executeAsFlow { getTomorrowListTaskUseCase() }.collect {
            when (it) {
                is Response.Error -> Unit
                Response.Loading -> Unit
                is Response.Result -> commit { copy(listTask = it.data.items) }
            }
        }
    }

    private fun getListTaskThisWeek() = async {
        executeAsFlow { getThisWeekListTaskUseCase() }.collect {
            when (it) {
                is Response.Error -> Unit
                Response.Loading -> Unit
                is Response.Result -> commit { copy(listTask = it.data.items) }
            }
        }
    }

    private fun getFinishListTask() = async {
        executeAsFlow {getFinishListTaskUseCase()}.collect {
            when (it) {
                is Response.Error -> Unit
                Response.Loading -> Unit
                is Response.Result -> commit { copy(listTask = it.data.items) }
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

            DashboardAction.GetAllListTask -> getAllTaskUseCase()
            DashboardAction.GetFinishListTask -> getFinishListTask()
            DashboardAction.GetListTaskThisWeek -> getListTaskThisWeek()
            DashboardAction.GetListTaskToday -> getListTaskToday()
            DashboardAction.GetListTaskTomorrow -> getTomorrowListTask()
        }
    }
}