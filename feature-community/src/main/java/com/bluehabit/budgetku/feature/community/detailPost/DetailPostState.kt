/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.community.detailPost

import android.os.Parcelable
import com.bluehabit.budgetku.data.model.CommentModel
import com.bluehabit.budgetku.data.model.post.PostModel
import com.bluehabit.budgetku.data.remote.dummy.dummyComment
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class DetailPostState(
    val comment: String = "",
    val replyTo:String="",
    //data
    val detailPost: @RawValue PostModel? = null,
    val comments: @RawValue List<CommentModel> = dummyComment
) : Parcelable


