/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.repositories

import com.bluehabit.eureka.data.common.Response
import com.bluehabit.eureka.data.persistence.SharedPref
import javax.inject.Inject

class SignOutRepository @Inject constructor(
    private val pref: SharedPref
) {
    suspend fun signOut():Response<Boolean>{
        pref.clearAllSession()

        return Response.Result(true)
    }
}