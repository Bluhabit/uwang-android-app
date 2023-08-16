/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.domain

import com.bluehabit.eureka.data.authentication.repositories.SignInRepository
import com.bluehabit.eureka.data.common.Response
import javax.inject.Inject

class SignInWithGoogleUseCase @Inject constructor(
    private val signInRepository: SignInRepository
) {
    suspend operator fun invoke(token: String): Response<Any> =
        signInRepository.signInWithGoogle(token)
}