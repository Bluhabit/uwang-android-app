/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.data.interceptor

import com.bluhabit.blu.data.persistence.SharedPref
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val sharedPref: SharedPref,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPref.getToken()
        val request = chain.request().newBuilder()
        request.addHeader("Authorization", "Bearer $token")
        return chain.proceed(request.build())
    }
}