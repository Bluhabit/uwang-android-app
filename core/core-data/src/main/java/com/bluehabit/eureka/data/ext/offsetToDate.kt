/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.ext

import android.util.Log
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter


fun String.offsetToDate(pattern: String): String? {
    return try {
        OffsetDateTime.parse(this).format(DateTimeFormatter.ofPattern(pattern))
    } catch (e: Exception) {
        Log.e("offsetToDate Ext", e.message.toString())
        null
    }
}