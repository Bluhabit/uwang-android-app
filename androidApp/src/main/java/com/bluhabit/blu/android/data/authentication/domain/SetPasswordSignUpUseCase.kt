/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.data.authentication.domain

import com.bluhabit.blu.android.data.authentication.repositories.AuthRepository
import com.bluhabit.blu.data.common.Response
import javax.inject.Inject

class SetPasswordSignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        password: String
    ): Response<Any> {
        return authRepository.setPasswordSignUp(password = password)
    }
}