/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.onboarding

import app.trian.mvi.ui.viewModel.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor(
) : MviViewModel<OnboardState,OnboardIntent, OnboardAction>(OnboardState()) {

    private fun calculatePager(page: Int) = async {
        val percentage = when (page) {
            0 -> 0.1f
            1 -> 0.2f
            2 -> 0.5f
            3 -> 0.7f
            4 -> 1f
            else -> 0.1f
        }
        commit { copy(percentage = percentage) }
    }


    override fun onAction(action: OnboardAction) {
        when (action) {
            is OnboardAction.PagerChanges -> calculatePager(action.page)
        }
    }

}