/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.sharedPref

class KMMPreference(private val context: KMMContext) {

    fun put(key: String, value: Int) {
        context.putInt(key, value)
    }

    fun put(key: String, value: String) {
        context.putString(key, value)
    }

    fun put(key: String, value: Boolean) {
        context.putBool(key, value)
    }

    fun getInt(key: String, default: Int): Int
            =  context.getInt(key, default)


    fun getString(key: String) : String?
            =  context.getString(key)


    fun getBool(key: String, default: Boolean): Boolean =
        context.getBool(key, default)

}
