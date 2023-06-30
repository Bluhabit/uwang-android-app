/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.detailPost

import androidx.lifecycle.SavedStateHandle
import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.budgetku.data.remote.dummy.dummyPosts
import com.bluehabit.core.ui.routes.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailPostViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : MviViewModel<DetailPostState, DetailPostIntent, DetailPostAction>(DetailPostState()) {

    private fun getId() = savedStateHandle.get<String>(Routes.DetailPost.argKey).orEmpty()

    private fun getDetailPost() = async {
        dummyPosts.find {
            it.id == getId()
        }?.let {
            commit {
                copy(detailPost = it)
            }
        }
    }

    override fun onAction(action: DetailPostAction) {
        when (action) {
            DetailPostAction.GetDetailPost -> getDetailPost()
        }
    }

}