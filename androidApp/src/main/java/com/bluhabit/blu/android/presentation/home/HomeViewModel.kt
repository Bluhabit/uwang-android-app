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

): BaseViewModel<HomeState, HomeAction, HomeEffect>(HomeState()) {
    override fun onAction(action: HomeAction) {
        when (action) {

            else -> {}
        }
    }

}