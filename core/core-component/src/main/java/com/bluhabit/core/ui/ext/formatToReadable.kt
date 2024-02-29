/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.ext

import java.text.NumberFormat
import java.util.Locale
import kotlin.math.round

fun Int.formatToReadable(): String {
    val num = this.toDouble()
    return when {
        num >= 1_000_000 -> intlFormat(num / 1_000_000) + "JT"
        num >= 1_000 -> intlFormat(num / 1_000) + "RB"
        else -> intlFormat(num)
    }
}

private fun intlFormat(num: Double): String {
    val formatter = NumberFormat.getInstance(Locale.getDefault())
    return formatter.format(round(num * 10) / 10)
}