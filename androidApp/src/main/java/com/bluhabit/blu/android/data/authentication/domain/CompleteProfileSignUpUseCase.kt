/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.data.authentication.domain

import com.bluhabit.blu.android.data.authentication.datasource.remote.response.UserCredentialResponse
import com.bluhabit.blu.android.data.authentication.repositories.AuthRepository
import com.bluhabit.blu.data.common.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class CompleteProfileSignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        fullName: String,
        dateOfBirth: LocalDate?,
        gender: String
    ): Response<UserCredentialResponse> {
        return authRepository.completeProfileSignUp(
            fullName = fullName,
            dateOfBirth = dateOfBirth?.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) ?: "",
            gender = gender
        )


    }
}