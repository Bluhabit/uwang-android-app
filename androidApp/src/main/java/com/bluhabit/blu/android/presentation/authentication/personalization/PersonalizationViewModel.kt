/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.personalization

import androidx.lifecycle.viewModelScope
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.common.BaseViewModel
import com.bluhabit.blu.android.data.authentication.domain.CompleteProfileSignUpUseCase
import com.bluhabit.blu.data.common.ResourceProvider
import com.bluhabit.core.ui.components.textfield.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PersonalizationViewModel @Inject constructor(
    private val completeProfileSignUpUseCase: CompleteProfileSignUpUseCase,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel<PersonalizationState, PersonalizationAction, PersonalizationEffect>(
    PersonalizationState()
) {
    override fun onAction(action: PersonalizationAction) {
        when (action) {
            PersonalizationAction.NextSkip -> nextStep()
            is PersonalizationAction.OnScreenChange -> updateState { copy(currentScreen = action.screen) }
            // Create Username Screen
            is PersonalizationAction.OnUsernameChange -> updateState {
                copy(
                    usernameValueState = action.value,
                    usernameState = when {
                        action.value.length < 3 ->
                            TextFieldState.Error(resourceProvider.getString(R.string.caption_error_field_username_min))

                        resourceProvider.isBadWord(action.value) ->
                            TextFieldState.Error(resourceProvider.getString(R.string.caption_error_field_username_badword))

                        action.value.length > 60 ->
                            TextFieldState.Error(resourceProvider.getString(R.string.caption_error_field_username_already))

                        else -> TextFieldState.None
                    }
                )
            }

            PersonalizationAction.CreateUsernameNextButton -> submitUsername()
            // Upload Photo Profile Screen
            is PersonalizationAction.OnProfileImageChange -> {
                updateState {
                    copy(
                        profileImage = action.value,
                    )
                }
                uploadPhotoProfile()
            }

            is PersonalizationAction.OnShowDialogChoice -> updateState { copy(showDialogChoice = action.show) }
            PersonalizationAction.UploadPhotoProfileNextButton -> submitPhotoProfileLink()
            // Choose Topic Screen
            is PersonalizationAction.OnAddSelectedList -> updateState {
                val newList = selectedTopicList.toMutableList()
                newList.add(action.topic)
                copy(selectedTopicList = newList)
            }

            PersonalizationAction.OnClearSelectedList -> updateState {
                val newList = selectedTopicList.toMutableList()
                newList.clear()
                copy(selectedTopicList = newList)
            }

            is PersonalizationAction.OnRemoveSelectedList -> updateState {
                val newList = selectedTopicList.toMutableList()
                newList.remove(action.topic)
                copy(selectedTopicList = newList)
            }

            PersonalizationAction.ChooseTopicNextButton -> submitTopic()
            // Choose Level Screen
            is PersonalizationAction.OnSelectedLevelChange -> updateState {
                copy(selectedIndex = action.index)
            }

            PersonalizationAction.ChooseLevelNextButton -> submitLevel()
        }
    }

    private fun submitLevel() {
        TODO("Not yet implemented")
    }

    private fun submitTopic() {
        TODO("Not yet implemented")
    }

    private fun submitPhotoProfileLink() {
        TODO("Not yet implemented")
    }

    private fun uploadPhotoProfile() {
        updateState {
            copy(
                uploadPhotoNextButton = true, // Jika sukses enable button
            )
        }
    }

    private fun submitUsername() {
        updateState { copy(createUsernameNextButton = false) }
    }

    private fun nextStep() = viewModelScope.launch {
        val currentStep = state.value.currentScreen
        if (currentStep < 0 || currentStep >= 2) {
            // Submit Data
        } else {
            updateState { copy(currentScreen = currentStep + 1) }
        }
    }

}