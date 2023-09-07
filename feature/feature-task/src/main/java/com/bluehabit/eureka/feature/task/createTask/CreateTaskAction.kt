/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.task.createTask

sealed interface CreateTaskAction {
    object CreateTemporaryTask : CreateTaskAction
    object UploadAttachment : CreateTaskAction
    object PublishTask : CreateTaskAction
    object AddNewSubTask : CreateTaskAction
}