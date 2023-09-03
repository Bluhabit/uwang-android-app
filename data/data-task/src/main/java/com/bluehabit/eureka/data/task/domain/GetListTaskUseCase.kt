/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.task.domain

import com.bluehabit.eureka.data.task.repositories.TaskRepository
import javax.inject.Inject

class GetListTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(page: Int) = taskRepository.getListTask(page)
}