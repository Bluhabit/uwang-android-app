/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.data.profile.repositories

import com.bluhabit.blu.data.persistence.SharedPref
import io.ktor.client.HttpClient
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val pref: SharedPref
) {}