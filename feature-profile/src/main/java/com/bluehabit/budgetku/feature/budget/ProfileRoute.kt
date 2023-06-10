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
import androidx.navigation.compose.navigation
import com.bluehabit.budgetku.feature.budget.editProfile.EditProfileViewModel
import com.bluehabit.budgetku.feature.budget.editProfile.ScreenEditProfile
import com.bluehabit.budgetku.feature.budget.profile.ProfileViewModel
import com.bluehabit.budgetku.feature.budget.profile.ScreenProfile
import com.bluehabit.core.ui.UIController
import com.bluehabit.core.ui.UIListenerData
import com.bluehabit.core.ui.pageWrapper
import com.bluehabit.core.ui.routes.ProfileConstants
import com.bluehabit.core.ui.routes.Routes.EditProfile
import com.bluehabit.core.ui.routes.Routes.Profile

fun NavGraphBuilder.profileRoute(
    uiController: UIController
) {

    navigation(
        route = ProfileConstants.parentRoute,
        startDestination = Profile.routeName
    ) {

        pageWrapper<ProfileViewModel>(
            route = Profile.routeName,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            val data by uiDataState.collectAsState()
            ScreenProfile(
                state=state,
                data=data,
                invoker = UIListenerData(
                    controller = uiController,
                    state = state,
                    data = data,
                    commit = ::commit,
                    commitData = ::commitData,
                    dispatcher = ::dispatch
                )
            )
        }
        pageWrapper<EditProfileViewModel>(
            route = EditProfile.routeName,
            controller = uiController
        ) {
            val state by uiState.collectAsState()
            val data by uiDataState.collectAsState()
            ScreenEditProfile(
                state=state,
                data=data,
                invoker = UIListenerData(
                    controller = uiController,
                    state = state,
                    data = data,
                    commit = ::commit,
                    commitData = ::commitData,
                    dispatcher = ::dispatch
                )
            )
        }

    }
}