/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.common

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat


fun Array<String>.checkGrantedPermissionFrom(ctx: Context): Boolean =
    map {
        ContextCompat.checkSelfPermission(ctx, it) == PackageManager.PERMISSION_GRANTED
    }.filter { it }.size >= this.size