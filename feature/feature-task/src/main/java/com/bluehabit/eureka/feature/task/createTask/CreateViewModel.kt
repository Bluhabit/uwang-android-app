/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.task.createTask

import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.eureka.data.common.executeAsFlow
import com.bluehabit.eureka.data.task.datasource.remote.request.SubtaskRequest
import com.bluehabit.eureka.data.task.domain.CreateTemporaryTaskUseCase
import com.bluehabit.eureka.data.task.domain.PublishTaskUseCase
import com.bluehabit.eureka.data.task.domain.UploadTaskAttachmentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(
    private val createTemporaryTaskUseCase: CreateTemporaryTaskUseCase,
    private val uploadTaskAttachmentUseCase: UploadTaskAttachmentUseCase,
    private val publishTaskUseCase: PublishTaskUseCase
) : MviViewModel<CreateTaskState, CreateTaskAction>(CreateTaskState()) {

    private fun createTemporaryTask() = async {
        executeAsFlow { createTemporaryTaskUseCase() }
            .collect {

            }
    }

    private fun uploadAttachment() = asyncWithState {
        executeAsFlow {
            uploadTaskAttachmentUseCase(
                file = File("")
            )
        }.collect {

        }
    }

    private fun publishTask() = asyncWithState {
        executeAsFlow {
            publishTaskUseCase(
                taskName = "",
                taskDescription = "",
                subtask = listOf()
            )
        }.collect {

        }
    }

    private fun addSubTask() = asyncWithState {
        val newSubTask: MutableList<SubtaskRequest> = listSubTask
        newSubTask.add(
            SubtaskRequest(
                subTaskName = "",
                done = false
            )
        )
        commit {
            copy(listSubTask = newSubTask)
        }
    }

    override fun onAction(action: CreateTaskAction) {
        when (action) {
            CreateTaskAction.CreateTemporaryTask -> createTemporaryTask()
            CreateTaskAction.PublishTask -> Unit
            CreateTaskAction.UploadAttachment -> Unit
            CreateTaskAction.AddNewSubTask -> addSubTask()
        }
    }
}