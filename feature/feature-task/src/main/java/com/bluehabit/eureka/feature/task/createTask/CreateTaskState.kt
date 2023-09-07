/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.task.createTask

import android.os.Parcelable
import app.trian.mvi.ui.extensions.Empty
import app.trian.mvi.ui.internal.contract.MviState
import com.bluehabit.eureka.data.task.datasource.remote.request.SubtaskRequest
import javax.annotation.concurrent.Immutable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Immutable
@Parcelize
data class CreateTaskState(
    //create task
    val titleTask: String = String.Empty,
    val titleTaskError: Boolean = false,
    val listSubTask: @RawValue List<SubtaskRequest> = mutableListOf(),
    val descriptionTask: String = String.Empty,
    val descriptionTaskError: Boolean = false,
    //end create task

    override val effect: @RawValue CreateTaskEffect = CreateTaskEffect.Nothing
) : MviState<CreateTaskEffect>(), Parcelable
