/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.personalization

import android.graphics.Bitmap
import com.bluhabit.blu.android.presentation.authentication.personalization.screen.SelectedTopic
import com.bluhabit.core.ui.components.textfield.TextFieldState
import com.bluhabit.core.ui.ext.Empty
import javax.annotation.concurrent.Immutable

@Immutable
data class PersonalizationState(

    //global
    val currentScreen: Int = 0,
    val showLoading: Boolean = false,
    // Create Username Screen
    val usernameState: TextFieldState = TextFieldState.None,
    val usernameValueState: String = String.Empty,
    val createUsernameNextButton: Boolean = true,
    // UploadPhotoProfileScreen
    val profileImage: Bitmap? = null,
    val showDialogChoice: Boolean = false,
    val uploadPhotoNextButton: Boolean = false,
    // Choose Topic Screen
    val selectedTopicList: List<SelectedTopic> = listOf(),
    val chooseTopicNextButtonEnabled: Boolean = true,
    // Choose Level Screen
    val selectedIndex: Int? = null,
    val chooseLevelNextButtonEnabled: Boolean = true,
)