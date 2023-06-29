/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.model.article

data class ArticleModel(
    val title: String,
    val body: String,
    val image: String,
    val date:String,
    val likes:Int
)
