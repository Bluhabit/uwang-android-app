/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.editprofile

import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.common.BaseViewModel
import com.bluhabit.blu.android.presentation.authentication.personalization.screen.SelectedTopic
import com.bluhabit.blu.data.common.ResourceProvider
import com.bluhabit.core.ui.components.textfield.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
) : BaseViewModel<EditProfileState, EditProfileAction, EditProfileEffect>(
    EditProfileState()
) {
    override fun onAction(action: EditProfileAction) {
        when (action) {
            is EditProfileAction.OnImageSelected ->
                updateState { copy(newImageProfileUri = action.bitmap) }

            is EditProfileAction.OnUploadProfileImageErrorVisibilityChange ->
                updateState { copy(uploadImageProfileErrorAlertVisibility = action.visible) }

            is EditProfileAction.OnUploadProfileImageSuccessVisibilityChange ->
                updateState { copy(uploadImageProfileSuccessAlertVisibility = action.visible) }
            is EditProfileAction.OnUsernameChange -> onUsernameChange(action.value)
            is EditProfileAction.OnFullNameChange -> onFullNameChange(action.value)
            is EditProfileAction.OnLinkChange -> updateState { copy(link = action.value) }
            is EditProfileAction.OnBioChange -> updateState {
                if (action.value.length <= 150) copy(
                    bio = action.value,
                    bioState = TextFieldState.None
                )
                else copy(
                    bioState = TextFieldState.Error(resourceProvider.getString(R.string.caption_error_field_bio_max))
                )
            }

            is EditProfileAction.OnRemoveSelectedTopic -> onRemoveSelectedTopic(action.selectedTopic)
            is EditProfileAction.OnScreenChange -> updateState { copy(currentScreen = action.screen) }
            EditProfileAction.OnEditNewTopic -> updateState {
                copy(
                    currentScreen = 1,
                    tempSelectedTopic = selectedTopic
                )
            }

            EditProfileAction.OnClearTempSelectedTopic -> updateState { copy(tempSelectedTopic = listOf()) }
            is EditProfileAction.OnAddTempSelectedTopic -> onAddTempSelectedTopic(action.selectedTopic)

            EditProfileAction.OnEditProfileSaved -> TODO()
            EditProfileAction.OnEditTopicSaved -> updateState {
                copy(
                    currentScreen = 0,
                    selectedTopic = tempSelectedTopic,
                )
            }

            is EditProfileAction.OnRemoveTempSelectedTopic -> onRemoveTempSelectedTopic(action.selectedTopic)
        }
    }

    private fun onRemoveSelectedTopic(topic: SelectedTopic) {
        updateState {
            val newList = selectedTopic.toMutableList()
            newList.remove(topic)
            copy(selectedTopic = newList)
        }
    }

    private fun onAddTempSelectedTopic(topic: SelectedTopic) {
        updateState {
            val newList = tempSelectedTopic.toMutableList()
            newList.add(topic)
            copy(tempSelectedTopic = newList)
        }
    }

    private fun onRemoveTempSelectedTopic(topic: SelectedTopic) {
        updateState {
            val newList = tempSelectedTopic.toMutableList()
            newList.remove(topic)
            copy(tempSelectedTopic = newList)
        }
    }

    private fun onFullNameChange(fullName: String) {
        updateState {
            copy(
                fullName = fullName,
                fullNameState = when {
                    fullName.length < 3 ->
                        TextFieldState.Error(resourceProvider.getString(R.string.caption_error_field_name_min))

                    resourceProvider.isBadWord(fullName) ->
                        TextFieldState.Error(resourceProvider.getString(R.string.caption_error_field_name_badword))

                    username.length > 60 ->
                        TextFieldState.Error(resourceProvider.getString(R.string.caption_error_field_name_max))

                    else -> TextFieldState.None
                }
            )
        }
    }

    private fun onUsernameChange(username: String) {
        updateState {
            copy(
                username = username,
//                usernameState = when {
//                    username.length < 3 ->
//                        TextFieldState.Error(resourceProvider.getString(R.string.caption_error_field_username_min))
//
//                    resourceProvider.isBadWord(username) ->
//                        TextFieldState.Error(resourceProvider.getString(R.string.caption_error_field_username_badword))
//
//                    username.length > 60 ->
//                        TextFieldState.Error(resourceProvider.getString(R.string.caption_error_field_username_max))
//
//                    else -> TextFieldState.None
//                }
            )
        }
    }
}