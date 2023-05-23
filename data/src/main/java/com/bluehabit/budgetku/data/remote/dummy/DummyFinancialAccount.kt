/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.remote.dummy

import com.bluehabit.budgetku.data.R
import com.bluehabit.budgetku.data.model.CategoryFinancialAccountModel
import com.bluehabit.budgetku.data.model.FinancialAccountModel

val dummyFinancialAccount = listOf(
    CategoryFinancialAccountModel(
        name = "Akun Cash",
        children = listOf(
            FinancialAccountModel(
                id = "001",
                name = "Cash",
                icon = R.drawable.dummy_category_dana_darurat
            )
        )
    ),
    CategoryFinancialAccountModel(
        name = "E-Wallet Populer",
        children = listOf(
            FinancialAccountModel(
                id = "002",
                name = "GoPay",
                icon = R.drawable.dummy_bank_gopay
            ),
            FinancialAccountModel(
                id = "003",
                name = "Ovo",
                icon = R.drawable.dummy_bank_ovo
            ),
            FinancialAccountModel(
                id = "004",
                name = "Shopee Pay",
                icon = R.drawable.dummy_bank_shopeepay
            )
        )
    ),
    CategoryFinancialAccountModel(
        name = "Bank Populer",
        children = listOf(
            FinancialAccountModel(
                id = "005",
                name = "Bank BRI",
                icon = R.drawable.dummy_bank_bri
            ),
            FinancialAccountModel(
                id = "006",
                name = "Bank Mandiri",
                icon = R.drawable.dummy_bank_mandiri
            ),
            FinancialAccountModel(
                id = "007",
                name = "Bank BNI",
                icon = R.drawable.dummy_bank_bni
            ),
            FinancialAccountModel(
                id = "008",
                name = "Bank BSI",
                icon = R.drawable.dummy_bank_bsi
            ),
            FinancialAccountModel(
                id = "009",
                name = "Bank Jago",
                icon = R.drawable.dummy_bank_jago
            )
        )
    )
)