/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.ext

import android.content.Context


fun Context.openEmail() {
    packageManager.getLaunchIntentForPackage("com.android.email")
        .apply {
            startActivity(this)
        }
}