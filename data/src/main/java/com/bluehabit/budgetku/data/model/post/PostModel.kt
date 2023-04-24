/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.model.post

import java.math.BigDecimal
import java.time.LocalDateTime

data class ContentBudgetingPostModel(
    val icon: Int,
    val name: String,
    val allocation: String,
    val amount: BigDecimal
)

data class PostModel(
    val displayName: String,
    val date: LocalDateTime,
    val body: String,
    val avatar: Int,
    val comments: Int,
    val likes: Int,
    val postType: Int,
    val mimeContent: Int,
    val content: List<ContentBudgetingPostModel>
)
