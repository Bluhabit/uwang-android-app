/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.dashboard.community

import android.os.Parcelable
import com.bluehabit.budgetku.data.model.article.ArticleModel
import com.bluehabit.budgetku.data.model.post.PostModel
import com.bluehabit.budgetku.data.remote.dummy.dummyArticlesCommunity
import com.bluehabit.budgetku.data.remote.dummy.dummyPosts
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class CommunityState(
    val selectedTab:Int=0,
    val selectedCategory:Int=0,
) : Parcelable

@Immutable
@Parcelize
data class CommunityDataState(
    val categories: @RawValue List<String> = listOf(
        "Terbaru",
        "Followed",
        "Semua",
        "Trending",
        "Untukmu"
    ),
    val posts:@RawValue List<PostModel> = dummyPosts,
    val articles: @RawValue List<ArticleModel> = dummyArticlesCommunity
) : Parcelable


sealed interface CommunityEvent{

}