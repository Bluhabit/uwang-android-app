/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.detailPost

import android.os.Parcelable
import com.bluehabit.budgetku.data.model.CommentModel
import com.bluehabit.budgetku.data.model.post.PostModel
import com.bluehabit.budgetku.data.remote.dummy.dummyComment
import com.bluehabit.budgetku.data.remote.dummy.dummyDetailPost
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class DetailPostState(
    val a: String = ""
) : Parcelable

@Immutable
@Parcelize
data class DetailPostDataState(
    val detailPost: @RawValue PostModel = dummyDetailPost,
    val comments: @RawValue List<CommentModel> = dummyComment
) : Parcelable

sealed interface DetailPostEvent {
}