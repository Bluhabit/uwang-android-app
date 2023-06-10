/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.createPost

import com.bluehabit.core.ui.viewModel.BaseViewModel
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
        }
    }

}