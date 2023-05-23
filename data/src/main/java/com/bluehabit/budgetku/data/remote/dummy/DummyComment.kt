/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.remote.dummy

import com.bluehabit.budgetku.data.R
import com.bluehabit.budgetku.data.model.CommentModel

val dummyComment:List<CommentModel> = listOf(
    CommentModel(
        fullName = "TrianDamain",
        avatar = R.drawable.dummy_avatar_1,
        time = "23 menit lalu",
        body = "Harus atur budgeting tuh kak biar ge boncos",
        likes = 2
    ),
    CommentModel(
        fullName = "Mariorasetyo",
        avatar = R.drawable.dummy_avatar_2,
        time = "30 menit lalu",
        body = "Hmm kira-kira mulai darimana ya",
        likes = 0
    ),
    CommentModel(
        fullName = "HeriHrys",
        avatar = R.drawable.dummy_avatar_3,
        time = "30 menit lalu",
        body = "Hmm kira-kira mulai darimana ya",
        likes = 0
    )
)