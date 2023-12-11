/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.common

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import com.bluhabit.blu.android.MainActivity

fun Context.findActivity(): MainActivity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context as MainActivity
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}