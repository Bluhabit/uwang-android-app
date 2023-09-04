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
import com.bluehabit.eureka.data.task.domain.GetListTaskByDateUseCase
import com.bluehabit.eureka.data.task.domain.GetListTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val getListTaskUseCase: GetListTaskUseCase,
    private val getListTaskByDateUseCase: GetListTaskByDateUseCase,
) : MviViewModel<DashboardState, DashboardAction>(DashboardState()) {

    init {

    }
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

    private fun getListTaskToday() = async {
        val today = LocalDate.now()
        executeAsFlow {
            getListTaskByDateUseCase(
                from = today,
                to = today.plusDays(1)
            )
        }.collect {
            when (it) {
                is Response.Error -> Unit
                Response.Loading -> Unit
                is Response.Result -> commit { copy(allTask = it.data.items) }
            }
        }
    }

    private fun getListTaskTomorrow() = async {
        val tomorrow = LocalDate.now().plusDays(1)
        executeAsFlow {
            getListTaskByDateUseCase(
                from = tomorrow,
                to = tomorrow.plusDays(1)
            )
        }.collect {
            when (it) {
                is Response.Error -> Unit
                Response.Loading -> Unit
                is Response.Result -> commit { copy(allTask = it.data.items) }
            }
        }
    }

    private fun getListTaskThisWeek() = async {
        val today = LocalDate.now()
        val field = WeekFields.of(Locale.forLanguageTag("ID")).dayOfWeek()
        val firstDay = today.with(field, 1)
        val lastDay = firstDay.plusDays(7)

        executeAsFlow {
            getListTaskByDateUseCase(
                from = firstDay,
                to = lastDay
            )
        }.collect {
            when (it) {
                is Response.Error -> Unit
                Response.Loading -> Unit
                is Response.Result -> commit { copy(allTask = it.data.items) }
            }
        }
    }

    private fun getListTaskByStatus() = async {
        val today = LocalDate.now()
        val field = WeekFields.of(Locale.forLanguageTag("ID")).dayOfWeek()
        val firstDay = today.with(field, 1)
        val lastDay = firstDay.plusDays(7)

        executeAsFlow {
            getListTaskByDateUseCase(
                from = firstDay,
                to = lastDay
            )
        }.collect {
            when (it) {
                is Response.Error -> Unit
                Response.Loading -> Unit
                is Response.Result -> commit { copy(allTask = it.data.items) }
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
            is DashboardAction.GetListTaskByStatus -> Unit
            DashboardAction.GetListTaskThisWeek -> getListTaskThisWeek()
            DashboardAction.GetListTaskToday -> getListTaskToday()
            DashboardAction.GetListTaskTomorrow -> getListTaskTomorrow()
        }
    }
}