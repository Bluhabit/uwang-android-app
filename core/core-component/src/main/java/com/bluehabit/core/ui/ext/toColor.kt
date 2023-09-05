/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.ext

import androidx.compose.ui.graphics.Color

fun String?.toColor(default:Color = Color.White): Color {
    return  if (this == null) {
        default
    } else {
        return Color(android.graphics.Color.parseColor(this))
    }
}