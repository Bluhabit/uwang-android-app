/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.datasource.remote

import io.ktor.resources.Resource

@Resource("/auth")
class AuthApi {
    @Resource("sign-in")
    class SignInWithEmail(val parent: AuthApi = AuthApi())
    @Resource("reset-password")
    class SetNewPassword(val parent: AuthApi = AuthApi())
}