/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.task.domain

import com.bluehabit.eureka.data.common.Response
import com.bluehabit.eureka.data.task.datasource.remote.response.TaskResponse
import com.bluehabit.eureka.data.task.repositories.TaskRepository
import java.time.OffsetDateTime
import java.time.temporal.WeekFields
import java.util.Locale
import javax.inject.Inject

class GetAllListTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(page: Int): Response<Map<String, List<TaskResponse>>> {
        return when (val result = taskRepository.getListTask(page)) {
            is Response.Result -> {
                if (result.data.items.isEmpty()) return Response.Result(mapOf())

                val today = OffsetDateTime.now()
                val field = WeekFields.of(Locale.forLanguageTag("ID")).dayOfWeek()
                val firstDay = today.with(field, 1)
                val lastDay = firstDay.plusDays(7)

                val filterNotEmpty = result.data.items.filter { !it.taskEnd.isNullOrEmpty() }
                val todayTask = filterNotEmpty.filter {
                    val date = OffsetDateTime.parse(it.taskEnd.orEmpty())
                    date.isAfter(today) && date.isBefore(today.plusDays(1))
                }

                val tomorrow = filterNotEmpty.filter {
                    val date = OffsetDateTime.parse(it.taskEnd.orEmpty())
                    date.isAfter(today.plusDays(1)) && date.isBefore(today.plusDays(2))
                }

                val thisWeek = filterNotEmpty.filter {
                    val date = OffsetDateTime.parse(it.taskEnd.orEmpty())
                    date.isAfter(firstDay) && date.isBefore(lastDay)
                }

                Response.Result(
                    mapOf(
                        "Hari ini" to todayTask,
                        "Besok" to tomorrow,
                        "Minggu ini" to thisWeek
                    )
                )
            }

            is Response.Error -> Response.Error("", 1)
            Response.Loading -> Response.Loading
        }
    }
}