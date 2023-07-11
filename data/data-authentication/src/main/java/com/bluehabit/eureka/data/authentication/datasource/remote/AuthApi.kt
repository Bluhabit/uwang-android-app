/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.datasource.remote

import io.ktor.resources.Resource

@Resource("/v1/auth")
class AuthApi {
    @Resource("sign-in-email")
    class SignInWithEmail(val parent: AuthApi = AuthApi())

    @Resource("sign-in-google")
    class SignInGoogle(val parent: AuthApi = AuthApi())

    @Resource("sign-up-email")
    class SignUpWithEmail(val parent: AuthApi = AuthApi())

    @Resource("sign-up-google")
    class SignUpWithGoogle(val parent: AuthApi = AuthApi())
}