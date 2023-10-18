/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.blu.data.ext

import android.util.Log
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun String.toDate(pattern: String): String? {
    return try {
        OffsetDateTime.parse(this).format(DateTimeFormatter.ofPattern(pattern))
    } catch (e: Exception) {
        Log.e("offsetToDate Ext", e.message.toString())
        null
    }
}

fun LocalDate.localDateToOffset(): String {
    val localDateTime = LocalDateTime.of(this, LocalTime.now())
    val offsetDateTime = OffsetDateTime.of(localDateTime, ZoneOffset.ofHours(-3))
    return offsetDateTime.toString()
}