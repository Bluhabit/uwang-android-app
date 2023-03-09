/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.sdk.user

import com.bluehabit.budgetku.DriverFactory
import com.bluehabit.budgetku.createDatabase
import com.bluehabit.budgetku.sharedPref.KMMPreference
import io.ktor.client.*

class UserSDK(
    driverFactory: DriverFactory,
    private val client: HttpClient,
    private val pref: KMMPreference
) {
    private val db = createDatabase(driverFactory)


}