/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.completeProfile

import androidx.lifecycle.viewModelScope
import com.bluhabit.blu.android.common.BaseViewModel
import com.bluhabit.blu.android.data.authentication.domain.CompleteProfileSignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CompleteProfileViewModel @Inject constructor(
    private val completeProfileSignUpUseCase: CompleteProfileSignUpUseCase
) : BaseViewModel<CompleteProfileState, CompleteProfileAction, CompleteProfileEffect>(
    CompleteProfileState()
) {
    override fun onAction(action: CompleteProfileAction) {
        when (action) {
            is CompleteProfileAction.OnDateOfBirthChange -> updateState {
                copy(
                    dateOfBirthState = action.value
                )
            }

            is CompleteProfileAction.SetPreferenceScreenPreferenceItem -> updateState {
                val updatedItem = preferenceItems[action.index].copy(checked = action.checked)
                val updatedList = preferenceItems.toMutableList()
                updatedList[action.index] = updatedItem
                copy(
                    preferenceItems = updatedList
                )
            }

            CompleteProfileAction.NextStep -> nextStep()
            is CompleteProfileAction.OnImageChange -> updateState { copy(avatar = action.value) }
            is CompleteProfileAction.OnScreenChange -> updateState { copy(currentScreen = action.screen) }
            is CompleteProfileAction.OnUsernameChange -> onUsernameChange(action.value)
            CompleteProfileAction.SubmitData -> submitData()
            // Upload Photo Profile Screen
            is CompleteProfileAction.OnProfileImageChange -> updateState { copy(profileImage = action.value) }
            is CompleteProfileAction.OnShowDialogChoice -> updateState { copy(showDialogChoice = action.show) }
            // Choose Topic Screen
            is CompleteProfileAction.OnAddSelectedList -> updateState {
                val newList = selectedTopicList.toMutableList()
                newList.add(action.topic)
                copy(selectedTopicList = newList)
            }
            CompleteProfileAction.OnClearSelectedList -> updateState {
                val newList = selectedTopicList.toMutableList()
                newList.clear()
                copy(selectedTopicList = newList)
            }
            is CompleteProfileAction.OnRemoveSelectedList -> updateState {
                val newList = selectedTopicList.toMutableList()
                newList.remove(action.topic)
                copy(selectedTopicList = newList)
            }
        }
    }

    private fun nextStep() = viewModelScope.launch {
        val currentStep = state.value.currentScreen
        if (currentStep < 0 || currentStep >= 2) {
            submitData()
        } else {
            updateState { copy(currentScreen = currentStep + 1) }
        }
    }

    private fun onUsernameChange(username: String) = viewModelScope.launch {
        updateState { copy(usernameState = username) }
    }

    private fun submitData() = viewModelScope.launch {
        if (state.value.avatar == null) {
            return@launch
        }

    }

}