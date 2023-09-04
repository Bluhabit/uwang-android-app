/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.task.domain

import com.bluehabit.eureka.data.common.Response
import com.bluehabit.eureka.data.model.BasePagingResponse
import com.bluehabit.eureka.data.task.datasource.remote.response.TaskResponse
import com.bluehabit.eureka.data.task.repositories.TaskRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.Locale
import javax.inject.Inject

class GetThisWeekListTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(): Response<BasePagingResponse<TaskResponse>> {
        val today = LocalDate.now()
        val field = WeekFields.of(Locale.forLanguageTag("ID")).dayOfWeek()
        val firstDay = today.with(field, 1)
        val lastDay = firstDay.plusDays(7)

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
       return taskRepository.getListTaskByDate(
            from = firstDay.format(formatter),
            to = lastDay.plusDays(1).format(formatter)
        )
    }
}