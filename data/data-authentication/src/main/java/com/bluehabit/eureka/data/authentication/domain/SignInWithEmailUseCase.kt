/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.domain

import com.bluehabit.eureka.data.authentication.datasource.remote.response.SignInResponse
import com.bluehabit.eureka.data.authentication.repositories.SignInRepository
import com.bluehabit.eureka.data.common.Response
import javax.inject.Inject

class SignInWithEmailUseCase @Inject constructor(
    private val signInRepository: SignInRepository
) {
    suspend operator fun invoke(email: String, password: String): Response<SignInResponse> =
        signInRepository.signInWIthEmail(email, password)
}