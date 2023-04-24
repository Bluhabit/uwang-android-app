/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.model.article

import java.time.LocalDateTime

data class ArticleModel(
    val title: String,
    val body: String,
    val image: Int,
    val date:LocalDateTime,
    val likes:Int
)
