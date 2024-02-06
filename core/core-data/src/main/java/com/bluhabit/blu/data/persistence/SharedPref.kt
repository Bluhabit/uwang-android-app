/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.data.persistence

import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPref(
    private val sharedPreferences: SharedPreferences
) {
    companion object {
        const val KEY_TOKEN = "abc0-df12"
        const val KEY_IS_LOGGED_IN = "f435-bc0f2"

    }

    fun setPersistData(key: String, value: String) = sharedPreferences.edit {
        putString(key, value)
        apply()
    }

    fun removePersistData(key: String) = sharedPreferences.edit {
        remove(key)
        commit()
    }

    fun getPersistData(key: String) = sharedPreferences.getString(key, "")

    fun setIsLoggedIn(isLoggedIn: Boolean) = sharedPreferences.edit {
        putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
        apply()
    }

    fun getIsLoggedIn(): Boolean =
        sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)

    fun setToken(token: String) = sharedPreferences.edit {
        putString(KEY_TOKEN, token)
        apply()
    }

    fun getToken(): String =
        sharedPreferences.getString(KEY_TOKEN, "")
            .orEmpty()

    fun setUserLoggedIn(
        token: String
    ) = sharedPreferences.edit {
        putBoolean(KEY_IS_LOGGED_IN, true)
        putString(KEY_TOKEN, token)
        apply()
    }

    fun saveSession(user: Map<String, Any>) {
        sharedPreferences.edit {
            user.forEach { (key, value) ->
                try {
                    when (value) {
                        is Int -> {
                            putInt(key, value)
                        }

                        is Float -> {
                            putFloat(key, value)
                        }

                        is String -> {
                            putString(key, value)
                        }

                        is Boolean -> {
                            putBoolean(key, value)
                        }

                        else -> {
                            putString(key, value.toString())
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            apply()
        }
    }

    private fun getKeyUserSession(): Array<String> {
        return arrayOf(
            "id",
            "authProvider",
            "email",
            "fullName",
            "username",
            "dateOfBirth",
            "gender",
            "profile",
            "status",
            "createdAt",
            "updatedAt"
        )
    }

    fun getSession(): HashMap<String, String> {
        val data = HashMap<String, String>()
        getKeyUserSession().forEach {
            val value = sharedPreferences.getString(it, "") ?: ""
            data[it] = value
        }
        return data
    }

    fun clearAllSession() = sharedPreferences.edit {
        clear()
        commit()
    }
}