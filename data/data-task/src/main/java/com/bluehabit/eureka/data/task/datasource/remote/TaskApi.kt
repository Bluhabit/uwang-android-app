/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.task.datasource.remote

import io.ktor.resources.Resource

@Resource("/api/v1/task")
class TaskApi {
    @Resource("priority-list")
    class GetListPriority(val parent: TaskApi = TaskApi(), val page: Int)

    @Resource("status-list")
    class GetListStatus(val parent: TaskApi = TaskApi(), val page: Int)

    @Resource("list-task")
    class GetListTask(val parent: TaskApi = TaskApi(), val page: Int)

    @Resource("get-list-by-date")
    class GetListTaskByDate(val parent: TaskApi = TaskApi(), val from: String, val to: String)

    @Resource("create-temporary-task")
    class CreateTemporaryTask(val parent: TaskApi = TaskApi())

    @Resource("upload-attachment")
    class UploadAttachment(val parent: TaskApi = TaskApi())

    @Resource("publish")
    class Publish(val parent: TaskApi = TaskApi())

    @Resource("task-list-by-status/{status}")
    class GetTaskByStatus(val parent: TaskApi = TaskApi(), val status: String, val page: Int)
}