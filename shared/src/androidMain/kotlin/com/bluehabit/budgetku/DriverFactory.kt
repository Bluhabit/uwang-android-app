/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import app.trian.sqldelight.Database

actual class DriverFactory(
    private val context: Context
) {
    actual fun createDriver(): SqlDriver = AndroidSqliteDriver(Database.Schema,context,"test.db")

}