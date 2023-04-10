/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.base.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal.formatToRupiah(): String {
    val format = NumberFormat.getCurrencyInstance(Locale("id","ID"))
    format.maximumFractionDigits =0
    return format.format(this)
}