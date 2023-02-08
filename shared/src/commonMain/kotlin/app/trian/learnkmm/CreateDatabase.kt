package app.trian.learnkmm

import app.trian.sqldelight.Database

fun createDatabase(driverFactory: DriverFactory): Database {
    val driver = driverFactory.createDriver()
    return Database(driver)
}