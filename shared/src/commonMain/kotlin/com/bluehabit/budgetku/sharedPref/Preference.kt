/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.sharedPref

expect fun KMMContext.putInt(key: String, value: Int)

expect fun KMMContext.getInt(key: String, default: Int): Int

expect fun KMMContext.putString(key: String, value: String)

expect fun KMMContext.getString(key: String) : String?

expect fun KMMContext.putBool(key: String, value: Boolean)

expect fun KMMContext.getBool(key: String, default: Boolean): Boolean