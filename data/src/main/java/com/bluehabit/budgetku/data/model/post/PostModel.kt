/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.model.post

import java.math.BigDecimal

data class ContentBudgetingPostModel(
    val icon: Int,
    val name: String,
    val allocation: String,
    val amount: BigDecimal
)

enum class PostType{
    TEXT,
    BUDGETING_TEMPLATE,
    IMAGE
}
data class PostModel(
    val displayName: String,
    val date: String,
    val body: String,
    val avatar: Int,
    val comments: Int,
    val likes: Int,
    val postType: PostType,
    val mimeContent: Int,
    val content: List<ContentBudgetingPostModel>
)
