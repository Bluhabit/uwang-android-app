package app.trian.learnkmm

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import app.trian.sqldelight.Database

actual class DriverFactory(
    private val context: Context
) {
    actual fun createDriver(): SqlDriver = AndroidSqliteDriver(Database.Schema,context,"test.db")

}