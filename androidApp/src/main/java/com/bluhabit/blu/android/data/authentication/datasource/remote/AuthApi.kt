/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.data.authentication.datasource.remote

import io.ktor.resources.Resource

@Resource("/api/auth")
class AuthApi {

    @Resource("/sign-in-google")
    class SignInGoogle(val parent: AuthApi = AuthApi())
}