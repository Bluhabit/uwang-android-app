/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createPost

import com.bluehabit.budgetku.android.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
) : BaseViewModel<CreatePostState, CreatePostEvent>(CreatePostState()) {

    init {
        handleActions()
    }

    override fun handleActions() = onEvent {
        when(it) {
            is CreatePostEvent.ChangePostVisibility -> commit {
                copy(
                    postVisibility = it.postVisibility,
                    isSubmitEnabled = true
                )
            }

            is CreatePostEvent.ChangeContentText -> commit {
                copy(contentText = it.contentText)
            }
        }
    }

}