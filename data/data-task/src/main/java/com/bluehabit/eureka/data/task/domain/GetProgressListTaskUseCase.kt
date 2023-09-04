/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.task.domain

import com.bluehabit.eureka.data.task.repositories.TaskRepository
import javax.inject.Inject

class GetProgressListTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(page: Int = 0, status: String = "e3daf232-2b2c-4720-9e87-7d23e869f8e5") = taskRepository.getTaskByStatus(page, status)
}