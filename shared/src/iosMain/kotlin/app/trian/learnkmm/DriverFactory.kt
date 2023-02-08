package app.trian.learnkmm

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import app.trian.sqldelight.Database

actual class DriverFactory {
    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            Database.Schema,"test.db")
}