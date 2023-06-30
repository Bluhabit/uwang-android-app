/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.community.createPost

import com.bluehabit.core.ui.components.bottomSheet.PostVisibility

sealed interface CreatePostAction {
    data class ChangePostVisibility(val postVisibility: PostVisibility): CreatePostAction
}