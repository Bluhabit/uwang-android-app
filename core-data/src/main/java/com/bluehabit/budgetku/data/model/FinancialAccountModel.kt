/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.model

data class CategoryFinancialAccountModel(
    val name:String="",
    val children:List<FinancialAccountModel>
)
data class FinancialAccountModel(
    val id:String="",
    val name: String = "",
    val icon: Int
)
