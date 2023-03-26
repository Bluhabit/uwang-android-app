/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.domain.auth

import android.content.SharedPreferences
import javax.inject.Inject

class CheckSessionUseCase @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    operator fun invoke(): Boolean = sharedPreferences.getBoolean("isLoggedIn", false)
}