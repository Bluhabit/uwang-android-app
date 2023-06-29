/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.remote.dummy

import com.bluehabit.budgetku.data.model.ChallengeModel

val dummyChallenge: List<ChallengeModel> = listOf(
    ChallengeModel(
        title = "Catat transaksi sebanyak-banyaknya",
        message = "Jadi lebih sadar akan pengeluaran dan pemasukan mulai dari sekarang.",
        progress = 0.4f,
        totalPoints = 1823,
        targetPoints = 2000,
        color = 0xFFFE1962,
        textColor = 0xFFFFFFFF,
        image = com.bluehabit.budgetku.data.R.drawable.ic_dummy_challenge,
    ),
    ChallengeModel(
        title = "Menabung Rutin Setiap Bulan",
        message = "Tingkatkan tabunganmu dan mulai menciptakan tujuan keuangan yang menginspirasi. ",
        progress = 0.5f,
        totalPoints = 1923,
        targetPoints = 2000,
        color = 0xFFFEC31F,
        textColor = 0xFF212121,
        image = com.bluehabit.budgetku.data.R.drawable.ic_dummy_challenge_2,
    )
)