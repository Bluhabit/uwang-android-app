/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.data.authentication.domain

import com.bluhabit.blu.data.datasource.remote.response.SignInGoogleResponse
import com.bluhabit.blu.android.data.authentication.repositories.AuthRepository
import com.bluhabit.blu.data.common.Response
import javax.inject.Inject

class SignInBasicUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String): Response<String> =
        authRepository.signInBasic(email)
}