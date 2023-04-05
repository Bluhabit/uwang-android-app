/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.domain.auth

import com.bluehabit.budgetku.data.common.Response
import com.bluehabit.budgetku.data.model.user.UserCredentialResponse
import com.bluehabit.budgetku.data.remote.auth.AuthDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SignInWIthGoogleUseCase @Inject constructor(
    private val authDataSource: AuthDataSource
) {
    operator fun invoke(token: String?) = flow {
        emit(Response.Loading)
        val result = authDataSource.signInWithGoogle(token.orEmpty())
        emit(result)
    }.flowOn(Dispatchers.IO)
}