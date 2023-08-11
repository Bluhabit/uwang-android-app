/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.domain

import com.bluehabit.eureka.data.authentication.datasource.remote.response.CompleteProfileResponse
import com.bluehabit.eureka.data.authentication.repositories.SignUpRepository
import com.bluehabit.eureka.data.common.Response
import javax.inject.Inject

class CompleteProfileUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(fullName: String, password: String): Response<CompleteProfileResponse> =
        signUpRepository.completeProfile(fullName, password)
}