/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import com.bluehabit.budgetku.feature.budget.createPost.CreatePostViewModel
import com.bluehabit.budgetku.feature.budget.createPost.ScreenCreatePost
import com.bluehabit.budgetku.feature.budget.detailPost.DetailPostViewModel
import com.bluehabit.budgetku.feature.budget.detailPost.ScreenDetailPost
import com.bluehabit.core.ui.UIController
import com.bluehabit.core.ui.UIListener
import com.bluehabit.core.ui.UIListenerData
import com.bluehabit.core.ui.pageWrapper
import com.bluehabit.core.ui.routes.Routes.CreatePost
import com.bluehabit.core.ui.routes.Routes.DetailPost

fun NavGraphBuilder.communityRoute(
    uiController: UIController
) {
        pageWrapper<DetailPostViewModel>(
            route = DetailPost.routeName(),
            arguments = DetailPost.navArg,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            val data by uiDataState.collectAsState()
            ScreenDetailPost(
                state=state,
                data=data,
                invoker = UIListenerData(
                    controller = uiController,
                    state =state,
                    data = data,
                    commit = ::commit,
                    commitData = ::commitData,
                    dispatcher = ::dispatch
                )
            )
        }

    pageWrapper<CreatePostViewModel>(
        route = CreatePost.routeName,
        controller = uiController
    ) {
        val state by uiState.collectAsState()
        ScreenCreatePost(
            state=state,
            invoker = UIListener(
                controller = uiController,
                state =state,
                commit = ::commit,
                dispatcher = ::dispatch
            )
        )
    }
}