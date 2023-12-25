/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.data.authentication.domain

import com.bluhabit.blu.android.data.authentication.datasource.remote.response.SignUpBasicResponse
import com.bluhabit.blu.android.data.authentication.repositories.AuthRepository
import com.bluhabit.blu.data.common.Response
import javax.inject.Inject

class VerifyOtpSignUpBasicUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(otp: String): Response<SignUpBasicResponse> =
        authRepository.verifyOtpSignUpBasic(otp)
}