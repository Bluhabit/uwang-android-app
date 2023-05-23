/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.remote.dummy

import com.bluehabit.budgetku.data.R
import com.bluehabit.budgetku.data.model.post.PostModel
import com.bluehabit.budgetku.data.model.post.PostType

val dummyDetailPost =  PostModel(
    displayName = "Mario Prasetyo",
    date = "23 Mei 2023,20:00",
    avatar = R.drawable.dummy_avatar_2,
    body = "Hi! Budgetku, aku mau curhat dong akhir akhir ini pengeluaran aku cukup naik karena harus pergi " +
            "ke cafe hampir di setiap minggu untuk diskusi. kira kira dari teman Budgetku ada tips gak ya?",
    comments = 12,
    likes = 321,
    content = listOf(),
    mimeContent = R.drawable.dummy_post,
    postType = PostType.IMAGE
)