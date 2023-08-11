/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.datasource.remote

import io.ktor.resources.Resource

@Resource("/api/v1/auth")
class AuthApi {
    @Resource("sign-in")
    class SignInWithEmail(val parent: AuthApi = AuthApi())

    @Resource("sign-in-google")
    class SignUpWithGoogle(val parent: AuthApi = AuthApi())

    @Resource("sign-up-email")
    class SignUpWithEmail(val parent: AuthApi = AuthApi())

    @Resource("otp-confirmation")
    class OtpConfirmation(val parent: AuthApi = AuthApi())

    @Resource("complete-profile")
    class CompleteProfile(val parent: AuthApi = AuthApi())


}