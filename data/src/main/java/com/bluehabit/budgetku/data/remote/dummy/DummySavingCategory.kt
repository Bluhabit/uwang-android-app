/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.remote.dummy

import com.bluehabit.budgetku.data.R
import com.bluehabit.budgetku.data.model.category.SavingCategory

val dummySavingCategory: List<SavingCategory> = listOf(
    SavingCategory(
        categoryName = "Belanja",
        icon = R.drawable.dummy_category_belanja
    ),
    SavingCategory(
        categoryName = "Liburan",
        icon = R.drawable.dummy_category_liburan
    ),
    SavingCategory(
        categoryName = "Rumah",
        icon = R.drawable.dummy_category_trumah
    ),
    SavingCategory(
        categoryName = "Menikah",
        icon = R.drawable.dummy_category_menikah
    ),
    SavingCategory(
        categoryName = "Pendidikan",
        icon = R.drawable.dummy_category_pendidikan
    ),
    SavingCategory(
        categoryName = "Dana Darurat",
        icon = R.drawable.dummy_category_dana_darurat
    ),
    SavingCategory(
        categoryName = "Kendaraan",
        icon = R.drawable.dummy_category_kendaraan
    ),
    SavingCategory(
        categoryName = "Pinjaman",
        icon = R.drawable.dummy_category_pinjaman
    ),
    SavingCategory(
        categoryName = "Kredit",
        icon = R.drawable.dummy_category_kredit
    ),
    SavingCategory(
        categoryName = "Investasi",
        icon = R.drawable.dummy_category_investasi
    )
)