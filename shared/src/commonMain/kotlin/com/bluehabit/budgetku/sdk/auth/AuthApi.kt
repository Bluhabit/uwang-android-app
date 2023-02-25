package com.bluehabit.budgetku.sdk.auth

import io.ktor.resources.*

@Resource("/v1/auth")
class AuthApi {
    @Resource("sign-in-email")
    class SignInWithEmail(val parent: AuthApi = AuthApi())

    @Resource("sign-in-google")
    class SignInGoogle(val parent: AuthApi = AuthApi())
}