/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku

import app.cash.sqldelight.db.SqlDriver


expect class DriverFactory{
    fun createDriver():SqlDriver
}

