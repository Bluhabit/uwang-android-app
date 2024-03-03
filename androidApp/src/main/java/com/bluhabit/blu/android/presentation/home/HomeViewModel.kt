/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.home

import com.bluhabit.blu.android.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : BaseViewModel<HomeState, HomeAction, HomeEffect>(HomeState()) {
    override fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnScreenChange -> updateState { copy(currentScreen = action.screen) }
            is HomeAction.OnBottomNavBadgeVisibilityChange ->
                updateState {
                    val newBottomNavigationItems = bottomNavigationItems.toMutableList()
                    val newBottomNavigationItem = bottomNavigationItems[action.index]
                            .copy(isBadgeVisible = action.visibility)
                    newBottomNavigationItems[action.index] = newBottomNavigationItem
                    copy(
                        bottomNavigationItems = newBottomNavigationItems
                    )
                }
        }
    }

}