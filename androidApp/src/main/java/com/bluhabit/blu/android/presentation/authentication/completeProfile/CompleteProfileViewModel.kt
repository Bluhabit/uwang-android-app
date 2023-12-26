/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.completeProfile

import androidx.lifecycle.viewModelScope
import com.bluhabit.blu.android.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CompleteProfileViewModel
@Inject constructor(): BaseViewModel<CompleteProfileState, CompleteProfileAction, CompleteProfileEffect>(
    CompleteProfileState()
) {
    override fun onAction(action: CompleteProfileAction) {
        when (action) {
            is CompleteProfileAction.OnDateOfBirthChange -> updateState { copy(
                dateOfBirthState = action.value
            ) }

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
        }
    }

    private fun nextStep()=viewModelScope.launch {

    }

    private fun onUsernameChange(username:String)=viewModelScope.launch {
        updateState { copy(usernameState=username) }
    }

    private fun submitData()=viewModelScope.launch {

    }

}