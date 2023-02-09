package com.bluehabit.budgetku

import app.trian.sqldelight.Database


fun createDatabase(driverFactory: DriverFactory): Database {
    val driver = driverFactory.createDriver()
    return Database(driver)
}