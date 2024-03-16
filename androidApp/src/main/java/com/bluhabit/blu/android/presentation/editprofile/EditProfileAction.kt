/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.editprofile

import android.graphics.Bitmap
import com.bluhabit.blu.android.presentation.authentication.personalization.screen.SelectedTopic

sealed interface EditProfileAction {
    // Used on more than 1 screen
    data class OnScreenChange(
        val screen: Int
    ): EditProfileAction

    // Input Edit Profile Screen
    data class OnImageSelected(
        val bitmap: Bitmap,
    ) : EditProfileAction
    data class OnUploadProfileImageErrorVisibilityChange(
        val visible: Boolean
    ) : EditProfileAction
    data class OnUploadProfileImageSuccessVisibilityChange(
        val visible: Boolean
    ) : EditProfileAction
    data class OnUsernameChange(
        val value: String
    ) : EditProfileAction
    data class OnFullNameChange(
        val value: String
    ) : EditProfileAction
    data class OnLinkChange(
        val value: String
    ) : EditProfileAction
    data class OnBioChange(
        val value: String
    ) : EditProfileAction
    data class OnRemoveSelectedTopic(
        val selectedTopic: SelectedTopic
    ) : EditProfileAction
    object OnEditProfileSaved : EditProfileAction

    // Edit Topic Screen
    object OnClearTempSelectedTopic : EditProfileAction
    object OnEditNewTopic : EditProfileAction
    data class OnAddTempSelectedTopic(
        val selectedTopic: SelectedTopic
    ) : EditProfileAction
    data class OnRemoveTempSelectedTopic(
        val selectedTopic: SelectedTopic
    ) : EditProfileAction
    object OnEditTopicSaved : EditProfileAction
}