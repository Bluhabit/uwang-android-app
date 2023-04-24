/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createPost

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class CreatePostState(
    val isSubmitEnabled: Boolean = false,
    val postVisibility: PostVisibility = PostVisibility.PUBLIC,
    val profileName: String = "Mario Prasetyo",
    val profilePicture: Uri = Uri.parse(""), // temporary
    val attachments: List<Uri> = listOf(), // temporary
    val contentText: String = "",
) : Parcelable


sealed class CreatePostEvent {
    data class ChangeContentText(val contentText: String): CreatePostEvent()
    data class ChangePostVisibility(val postVisibility: PostVisibility): CreatePostEvent()
}