/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.detailPost

import androidx.lifecycle.SavedStateHandle
import com.bluehabit.budgetku.data.remote.dummy.dummyPosts
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.viewModel.BaseViewModelData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailPostViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : BaseViewModelData<DetailPostState, DetailPostDataState, DetailPostEvent>(DetailPostState(), DetailPostDataState()) {
    init {
        handleActions()
    }

    private fun getId() = savedStateHandle.get<String>(Routes.DetailPost.argKey).orEmpty()

    private fun getDetailPost() = async {
        dummyPosts.find {
            it.id == getId()
        }?.let {
            commitData {
                copy(detailPost = it)
            }
        }
    }

    override fun handleActions() = onEvent { event ->
        when (event) {
            DetailPostEvent.GetDetailPost -> getDetailPost()
        }
    }

}