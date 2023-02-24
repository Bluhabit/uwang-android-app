package com.bluehabit.budgetku.sdk.user

import com.bluehabit.budgetku.DriverFactory
import com.bluehabit.budgetku.createDatabase
import com.bluehabit.budgetku.entity.UserModel
import com.bluehabit.budgetku.model.UserResponse
import com.bluehabit.budgetku.sharedPref.KMMPreference
import io.ktor.client.*

class UserSDK(
    private val driverFactory: DriverFactory,
    private val client: HttpClient,
    private val pref: KMMPreference
) {
    private val db = createDatabase(driverFactory)


}