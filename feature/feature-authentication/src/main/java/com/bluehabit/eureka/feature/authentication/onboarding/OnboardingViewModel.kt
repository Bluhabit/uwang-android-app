/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.onboarding

import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.eureka.data.authentication.domain.CheckSessionUseCase
import com.bluehabit.eureka.data.common.Response
import com.bluehabit.eureka.data.common.executeAsFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val checkSessionUseCase: CheckSessionUseCase
) : MviViewModel<OnboardingState, OnboardingAction>(
    OnboardingState()
) {
    override fun onAction(action: OnboardingAction) {
        when (action) {
            OnboardingAction.CheckSession -> async {
                executeAsFlow { checkSessionUseCase() }
                    .collect {
                        when (it) {
                            is Response.Error -> commit { copy(isLoading = false) }
                            Response.Loading -> commit { copy(isLoading = true) }
                            is Response.Result -> if (it.data) {
                                commit {
                                    copy(
                                        isLoading=false,
                                        effect = OnboardingEffect.NavigateToHome
                                    )
                                }
                            }else{
                                commit { copy(isLoading = false) }
                            }
                        }
                    }
            }
        }
    }
}