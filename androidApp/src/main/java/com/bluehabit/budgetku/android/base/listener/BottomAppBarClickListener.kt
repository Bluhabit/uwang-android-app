/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.base.listener

fun interface BottomAppBarClickListener {
    fun onItemClick(id:String,vararg params:String)
}