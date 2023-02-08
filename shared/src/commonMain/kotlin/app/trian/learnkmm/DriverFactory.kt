package app.trian.learnkmm

import app.cash.sqldelight.db.SqlDriver


expect class DriverFactory{
    fun createDriver():SqlDriver
}

