/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.repositories

import com.bluehabit.eureka.data.local.SharedPref
import com.bluehabit.eureka.data.remote.auth.AuthApi
import com.bluehabit.eureka.data.common.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.plugins.resources.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class SignInWithEmailRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val pref: SharedPref
) {
    suspend fun execute(email:String,password:String)= safeApiCall<Any> { httpClient.post(AuthApi.SignInWithEmail()) {
        setBody(
            mapOf(
                "email" to email,
                "password" to password
            )
        )
    } }
}