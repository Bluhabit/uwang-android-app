/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.remote.dummy

import com.bluehabit.budgetku.data.model.tutorial.TutorialBudgetModel

val dummyTutorial: List<TutorialBudgetModel> = listOf(
    TutorialBudgetModel(
        title = "Cara Transaksi di Budgetku",
        image = com.bluehabit.budgetku.data.R.drawable.ic_dummy_tutorial
    ),
    TutorialBudgetModel(
        title = "Cara Atur Anggran",
        image = com.bluehabit.budgetku.data.R.drawable.ic_dummy_tutorial_2
    ),
    TutorialBudgetModel(
        title = "Cara Buat Tujuan",
        image = com.bluehabit.budgetku.data.R.drawable.ic_dummy_tutorial_3
    )
)