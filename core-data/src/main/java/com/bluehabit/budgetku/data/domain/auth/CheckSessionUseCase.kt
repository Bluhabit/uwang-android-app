/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.domain.auth

import com.bluehabit.budgetku.data.local.SharedPref
import javax.inject.Inject

class CheckSessionUseCase @Inject constructor(
    private val pref: SharedPref
) {
    operator fun invoke(): Boolean = pref.getIsLoggedIn()
}