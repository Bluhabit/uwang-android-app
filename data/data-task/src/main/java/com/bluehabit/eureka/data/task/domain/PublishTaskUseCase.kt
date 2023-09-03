/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.task.domain

import com.bluehabit.eureka.data.task.datasource.remote.request.PublishTaskRequest
import com.bluehabit.eureka.data.task.datasource.remote.request.SubtaskRequest
import com.bluehabit.eureka.data.task.repositories.TaskRepository
import javax.inject.Inject

class PublishTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(
        taskName:String,
        taskDescription:String,
        subtask:List<SubtaskRequest>,
        priorityId:String?=null
    ) = taskRepository.publishTask(
        PublishTaskRequest(
            taskId = "",//wil be added in repository
            taskDescription = taskDescription,
            subtask = subtask,
            taskName = taskName,
            priorityId = priorityId
        )
    )
}