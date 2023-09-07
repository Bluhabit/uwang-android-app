/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.ext

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZoneOffset

fun LocalDate.localDateToOffset(): String {
    val localDateTime = LocalDateTime.of(this, LocalTime.now())
    val offsetDateTime = OffsetDateTime.of(localDateTime, ZoneOffset.ofHours(-3))
    return offsetDateTime.toString()
}